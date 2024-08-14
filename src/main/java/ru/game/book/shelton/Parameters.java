package ru.game.book.shelton;

import lombok.Data;

@Data
public class Parameters {
    private Integer mastery;
    private Integer currentMastery;
    private Integer stamina;
    private Integer currentStamina;
    private Integer luck;
    private Integer currentLuck;
    private Integer money;
    private Integer food;

    public void init() {
        mastery = Utils.rollDice() + 6;
        currentMastery = mastery;
        stamina = Utils.rollTwoDice() + 12;
        currentStamina = stamina;
        luck = Utils.rollDice() + 6;
        currentLuck = luck;
        money = 15;
        food = 0;
    }
}
