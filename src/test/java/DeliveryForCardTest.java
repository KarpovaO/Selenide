import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryForCardTest {
    public String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldRegisterCardWithDelivery() {

//        LocalDate meetingDate = LocalDate.now().plusDays(3);
//        String meetingDateStr = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        String planningDate = generateDate(3, "dd.MM.yyyy");

        open("http://localhost:9999");

        $("[data-test-id= 'city'] input").setValue("Москва");
        $("div.menu div.menu-item span.menu-item__control").click();
//        $("[placeholder='Дата встречи']").setValue("").setValue(planningDate);
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("td.calendar__day.calendar__day_state_current").click();
        $("[data-test-id= 'name'] input").setValue("Иванов Иван");
        $("[data-test-id= 'phone'] input").setValue("+79161112233");
        $("[data-test-id= 'agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id= 'notification']").shouldBe(visible, Duration.ofSeconds(14));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }


}
