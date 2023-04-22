package uz.tafakkoor.easyorder.dtos.restaurant;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTime {

    private int hour;
    private int minute;

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

}
