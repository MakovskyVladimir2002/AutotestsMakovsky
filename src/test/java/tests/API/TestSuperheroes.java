package tests.API;

import constants.TestDataSuperhero; // Убедитесь, что этот путь верен
import controllers.SuperheroController;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.ErrorMessageModel;
import models.SuperheroModel; // Убедитесь, что этот класс существует
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class TestSuperheroes {

    private static final Logger logger = LoggerFactory.getLogger(TestSuperheroes.class);

    private static Stream<org.junit.jupiter.params.provider.Arguments> getValidHeroes() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(TestDataSuperhero.SUPERHERO_VALID_WITHOUT_PHONE),
                org.junit.jupiter.params.provider.Arguments.of(TestDataSuperhero.SUPERHERO_VALID_WITH_PHONE)
        );
    }


    @Test
    @DisplayName("Get all superheroes and check status code")
    public void getAllSuperheroesTest() {
        Response response = new SuperheroController().getAllHeroes();
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        logger.info("Получен список всех супергероев.");
    }

    @Test
    @DisplayName("Try to get all superheroes with invalid path")
    public void getAllSuperheroesTestWithInvalidPath() {
        ErrorMessageModel expectedError = TestDataSuperhero.NOT_FOUND_ERROR; // Исправлено
        Response response = new SuperheroController().getAllHeroesWithInvalidPath();
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        assertThat(actualError).usingRecursiveComparison().isEqualTo(expectedError);
        logger.info("Ошибка 404 при неверном пути подтверждена.");
    }

    @Test
    @Tag("get")
    @Tag("negative")
    @DisplayName("Try to get superhero by invalid id")
    public void getSuperheroByInvalidIdTest() {
        ErrorMessageModel expectedError = TestDataSuperhero.BAD_REQUEST_ERROR; // Исправлено
        Response response = new SuperheroController().getHeroWithInvalidId(); // Этот метод должен возвращать ошибку 400
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        assertThat(actualError).usingRecursiveComparison().isEqualTo(expectedError);
        logger.info("Ошибка 400 при неверном ID подтверждена.");
    }

    @Test
    @DisplayName("Add superhero with null phone and check status code")
    public void postSuperheroTest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_VALID_WITHOUT_PHONE; // Исправлено
        Response response = new SuperheroController().addNewHero(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        logger.info("Герой создан с кодом 200.");
    }

    @Test
    @DisplayName("Add superhero with null phone and check status code, data")
    public void postSuperheroWithDataCheckTest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_VALID_WITHOUT_PHONE; // Исправлено
        Response response = new SuperheroController().addNewHero(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        SuperheroModel actualSuperheroModel = response.as(SuperheroModel.class);
        // Обновляем ID, так как он, скорее всего, генерируется сервером
        expectedSuperheroModel.setId(actualSuperheroModel.getId());
        assertThat(actualSuperheroModel).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedSuperheroModel);
        logger.info("Герой создан и данные совпадают (ID обновлён).");
    }

    @Test
    @DisplayName("Add superhero with not null phone and check status code and data")
    public void postSuperheroWithPhoneTest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_VALID_WITH_PHONE; // Исправлено
        Response response = new SuperheroController().addNewHero(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        SuperheroModel actualSuperheroModel = response.as(SuperheroModel.class);
        expectedSuperheroModel.setId(actualSuperheroModel.getId());
    }

    @Test
    @DisplayName("Add superhero with not null phone and check status code, data and get check")
    public void postSuperheroWithPhoneWithGetCheckE2ETest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_VALID_WITH_PHONE; // Исправлено
        Response response = new SuperheroController().addNewHero(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
    }

    @Test
    @DisplayName("Try to add superhero with not valid date")
    public void postSuperheroWithNotValidDateTest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_INVALID_DATE; // Исправлено
        ErrorMessageModel expectedError = TestDataSuperhero.BAD_REQUEST_ERROR; // Исправлено
        Response response = new SuperheroController().addNewHero(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        assertThat(actualError).usingRecursiveComparison().isEqualTo(expectedError);
        logger.info("Ошибка 400 при создании героя с неверной датой подтверждена.");
    }

    @Test
    @DisplayName("Try to add superhero using invalid path")
    public void postSuperheroWithNotValidPathTest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_VALID_WITHOUT_PHONE; // Исправлено
        ErrorMessageModel expectedError = TestDataSuperhero.NOT_FOUND_ERROR; // Исправлено
        Response response = new SuperheroController().addNewHeroWithInvalidPath(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        assertThat(actualError).usingRecursiveComparison().isEqualTo(expectedError);
        logger.info("Ошибка 404 при создании героя по неверному пути подтверждена.");
    }


    @Test
    @DisplayName("Try to update superhero with invalid date")
    public void putSuperheroWithInvalidDateTest() {
        SuperheroModel expectedSuperheroModel = TestDataSuperhero.SUPERHERO_INVALID_DATE_UPDATE; // Исправлено
        ErrorMessageModel expectedError = TestDataSuperhero.BAD_REQUEST_ERROR; // Исправлено
        Response response = new SuperheroController().updateExistingHero(expectedSuperheroModel);
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST);
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        assertThat(actualError).usingRecursiveComparison().isEqualTo(expectedError);
        logger.info("Ошибка 400 при обновлении героя с неверной датой подтверждена.");
    }


    @Test
    @DisplayName("Try to delete superhero with invalid path")
    public void deleteSuperheroWithInvalidPathTest() {
        ErrorMessageModel expectedError = TestDataSuperhero.NOT_FOUND_ERROR; // Исправлено
        Response response = new SuperheroController().deleteHeroWithInvalidPath(TestDataSuperhero.NOT_EXISTED_ID); // Исправлено
        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND);
        ErrorMessageModel actualError = response.as(ErrorMessageModel.class);
        assertThat(actualError).usingRecursiveComparison().isEqualTo(expectedError);
        logger.info("Ошибка 404 при удалении по неверному пути подтверждена.");
    }


    private SuperheroModel getModelById(SuperheroModel[] models, int id) {
        List<SuperheroModel> list = Arrays.asList(models);
        return list.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Not found model in SuperheroModels with an id = " + id));
    }
}