package com.springboot.learning.lombok;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.AccessLevel;
/*@Data*/
@Setter @Getter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode @Builder
public class LombokData {

    private  Long id; // will be set when persisting
    @Setter(AccessLevel.PROTECTED)
    private String firstName;
    private String lastName;
    private int age;

    @Override
    public String toString() {
        return "LombokData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
