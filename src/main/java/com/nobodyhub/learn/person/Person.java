package com.nobodyhub.learn.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author Ryan
 * @since 30/01/2018
 */
@Data
@AllArgsConstructor
@ToString
public class Person {
    private String firstName;
    private String lastName;
}
