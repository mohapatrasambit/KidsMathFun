package org.sambit.kids.math.equations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Equation {
    private Integer left;
    private Integer right;
    private String operator;
}
