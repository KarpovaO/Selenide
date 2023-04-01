import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryForCardTest {
  
    @Test
    void shouldRegisterCardWithDelivery() {
        LocalDate meetingDate = LocalDate.now().plusDays(3);
        String meetingDateStr = meetingDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        open("http://localhost:9999");

        $("[data-test-id= 'city'] input").setValue("Москва");
        $("div.menu div.menu-item span.menu-item__control").click();
        $("[placeholder='Дата встречи']").setValue("").setValue(meetingDateStr);
        $("td.calendar__day.calendar__day_state_current").click();
        $("[data-test-id= 'name'] input").setValue("Иванов Иван");
        $("[data-test-id= 'phone'] input").setValue("+79161112233");
        $("[data-test-id= 'agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id= 'notification']").shouldBe(visible, Duration.ofSeconds(14));

    }


}
