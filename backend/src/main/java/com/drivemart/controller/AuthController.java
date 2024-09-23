package com.drivemart.controller;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth") // Все маршруты будут начинаться с /auth
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Возвращает имя HTML-файла логина
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // Возвращает имя HTML-файла регистрации
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        // URL для получения токена в Keycloak
        String tokenUrl = "http://localhost:8180/realms/drivemart-realm/protocol/openid-connect/token";

        // Формируем параметры для запроса
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", "your-client-id");
        body.add("client_secret", "your-client-secret"); // если требуется
        body.add("username", username);
        body.add("password", password);

        // Создаем объект RestTemplate для выполнения HTTP-запросов
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Отправляем POST-запрос для получения токена
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, new HttpEntity<>(body, createHeaders()), Map.class);

            // Проверяем, успешен ли запрос
            if (response.getStatusCode() == HttpStatus.OK) {
                // Аутентификация успешна
                Map<String, Object> tokenData = response.getBody();
                String accessToken = (String) tokenData.get("access_token");

                // Здесь можно сохранить токен в сессии или контексте безопасности
                model.addAttribute("token", accessToken);

                // Перенаправляем на защищённую страницу
                return "redirect:/static/authenticate.html";
            } else {
                // В случае ошибки аутентификации
                model.addAttribute("error", "Неверные логин или пароль");
                return "redirect:/auth/login?error";
            }

        } catch (RestClientException e) {
            // Обработка ошибки запроса
            model.addAttribute("error", "Ошибка соединения с сервером аутентификации");
            return "redirect:/auth/login?error";
        }
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            Model model) {

        boolean isRegistered = registerUserInKeycloak(firstName, lastName, email, phone, password);

        if (isRegistered) {
            return "redirect:/auth/login"; // Успешная регистрация — перенаправляем на логин
        } else {
            model.addAttribute("error", "Ошибка регистрации. Попробуйте снова.");
            return "register"; // Остаемся на странице регистрации с сообщением об ошибке
        }
    }

    private boolean registerUserInKeycloak(String firstName, String lastName, String email, String phone, String password) {
        try {
            // URL для создания нового пользователя в Keycloak
            String keycloakUrl = "http://localhost:8180/auth/admin/realms/drivemart-realm/users";

            // Получите токен администратора для авторизации запроса
            String adminAccessToken = getAdminAccessToken();

            // Создание нового пользователя
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(adminAccessToken);

            // Создание тела запроса для создания пользователя
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("firstName", firstName);
            requestBody.put("lastName", lastName);
            requestBody.put("email", email);
            requestBody.put("enabled", true); // Включаем пользователя
            requestBody.put("username", email); // Используем email как логин

            // Добавление информации о телефоне и других атрибутах
            Map<String, String> attributes = new HashMap<>();
            attributes.put("phone", phone);
            requestBody.put("attributes", attributes);

            // Добавление учётных данных (пароля)
            List<Map<String, Object>> credentials = new ArrayList<>();
            Map<String, Object> passwordCredentials = new HashMap<>();
            passwordCredentials.put("type", "password");
            passwordCredentials.put("value", password);
            passwordCredentials.put("temporary", false); // Пароль не является временным
            credentials.add(passwordCredentials);
            requestBody.put("credentials", credentials);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            // Отправляем запрос в Keycloak
            ResponseEntity<String> response = restTemplate.postForEntity(keycloakUrl, request, String.class, "{realm}");

            // Если запрос прошел успешно (код 201), пользователь создан
            return response.getStatusCode() == HttpStatus.CREATED;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getAdminAccessToken() {
        RestTemplate restTemplate = new RestTemplate();

        // URL для получения токена
        String keycloakTokenUrl = "http://localhost:8180/auth/realms/drivemart-realm/protocol/openid-connect/token";

        // Заголовки запроса
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Параметры тела запроса
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "your-client-id"); // Идентификатор вашего клиента
        body.add("client_secret", "your-client-secret"); // Секрет вашего клиента
        body.add("grant_type", "client_credentials"); // Тип запроса на получение токена

        // Создаем HTTP-запрос
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // Выполняем запрос
        ResponseEntity<Map> response = restTemplate.postForEntity(keycloakTokenUrl, request, Map.class, "{realm}");

        // Извлекаем токен из ответа
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return (String) responseBody.get("access_token"); // Возвращаем токен
        } else {
            throw new RuntimeException("Не удалось получить токен администратора: " + response.getStatusCode());
        }
    }



    // Метод для создания заголовков запроса
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

}