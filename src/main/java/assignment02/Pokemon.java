package assignment02;

import java.util.Objects;

public class Pokemon {
    private static final String END_OF_LINE = System.lineSeparator();

    // POKÉMON ATTRIBUTES
    private String pokemonName;
    private final int MAX_HP;
    private int currentHP;
    private int currentEP;
    private final Types type;
    private Skills skill;

    private final static int MAX_EP = 100;

    // CONSTANTS
    private static final int REST_HEALING_POWER = 20;
    private static final int RECOVER_EP = 25;

    public Pokemon(String pokemonName, int maxHP, String type) {
        this.pokemonName = pokemonName;
        this.MAX_HP = maxHP;
        this.currentHP = MAX_HP;
        this.currentEP = MAX_EP;
        this.type = Types.valueOf(type.toUpperCase());
        this.skill = null;
    }


    // Creates a skill and ties it to the Pokémon
    public void learnSkill(String skillName, int attackPower, int energyCost) {
        this.skill = new Skills(skillName, attackPower, energyCost); }

    // Removes the current skill from the Pokémon
    public void forgetSkill() { this.skill = null; }

    // Checks if the Pokémon knows a skill
    public boolean knowsSkill() { return this.skill != null; }

    // Method to attack and damage another Pokémon
    public String attack (Pokemon targetPokemon) {
        if (currentHP == 0) {
            return "Attack failed. " + this.getName() + " fainted.";
        } else if (targetPokemon.currentHP == 0) {
            return "Attack failed. " + targetPokemon.pokemonName + " fainted.";
        } else if (this.skill == null) {
            return "Attack failed. " + this.pokemonName + " does not know a skill.";
        } else if (this.currentEP < this.skill.getEnergyCost()) {
            return "Attack failed. " + this.pokemonName + " lacks energy: " + this.currentEP + "/" + this.skill.getEnergyCost();
        } else {
            this.spendEnergyPoints();
            targetPokemon.receiveDamage(this);
            return this.pokemonName + " uses " + this.skill.getSkillName() + " on " + targetPokemon.getName() + "."
                    + skill.typeEffectivenessMessage(this, targetPokemon) + END_OF_LINE +
                    targetPokemon.pokemonName + " has " + targetPokemon.currentHP + " HP left." + skill.willPokemonFaint(targetPokemon);
        }
    }


    // Removes the energy cost of the attack from the attacking Pokémons current energy
    // This method also needs to be passed a <skill_identifier> if expanding the program to support Pokémon having multiple skills
    public void spendEnergyPoints (){ this.currentEP -= this.skill.getEnergyCost(); }

    // Pokémon recovers a fixed amount of energy (unless fainted)
    public void recoverEnergy(){
        if(this.currentHP > 0){
            if(this.currentEP + RECOVER_EP >= MAX_EP){
                this.currentEP = MAX_EP;
            } else {
                this.currentEP += RECOVER_EP;
            }
        }
    }


    // Subtracts the incoming damage from the defending Pokémons HP
    public void receiveDamage (Pokemon attacker) {
        int totalDamage = (int) Math.floor(attacker.skill.getAttackPower() * attacker.skill.typeMultiplier(attacker, this));
        int afterAttackHP = this.currentHP - totalDamage;
        this.currentHP = Math.max(afterAttackHP, 0);
    }

    // Pokémon heals a fixed amount of HP (unless fainted)
    public void rest(){
        if(this.currentHP > 0){
            if(this.currentHP + REST_HEALING_POWER >= this.MAX_HP){
                this.currentHP = this.MAX_HP;
            } else {
                this.currentHP += REST_HEALING_POWER;
            }
        }
    }

    // Pokémon uses a healing item to increase its HP
    public String useItem(Item item) {
        if (currentHP == MAX_HP) {
            return this.pokemonName + " could not use " + item.getItemName() + ". HP is already full.";
        } else {
            int afterHealingHP = currentHP + item.getPowerValue();
            if (afterHealingHP >= MAX_HP) {
                int amountHealed = this.MAX_HP - this.currentHP;
                currentHP = MAX_HP;
                return pokemonName + " used " + item.getItemName() + ". It healed " + amountHealed + " HP.";
            } else {
                currentHP = afterHealingHP;
                return pokemonName + " used " + item.getItemName() + ". It healed " + item.getPowerValue() + " HP.";
            }
        }
    }


    // Returns true if two Pokémon are equal
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        } else if (anotherObject == null) {
            return false;
            } else if (anotherObject instanceof Pokemon) {
                Pokemon otherPokemon = (Pokemon) anotherObject; // if using intelliJ's suggestion - do both ways work just the same?
                boolean samePokemonName = this.pokemonName.equals(otherPokemon.pokemonName); // check these two errors
                boolean sameType = this.type.equals(otherPokemon.type);  // should just use "normal" equals - not null safe?
                boolean sameCurrentHP = this.currentHP == otherPokemon.currentHP;
                boolean sameMAX_HP = this.MAX_HP == otherPokemon.MAX_HP;
                boolean sameCurrentEP = this.currentEP == otherPokemon.currentEP;
                boolean sameSkill = Objects.equals(this.skill, otherPokemon.skill);
                return samePokemonName && sameType && sameCurrentHP && sameMAX_HP && sameCurrentEP && sameSkill;
        } else {
            return false;
        }
    }

    public String toString() {
        if (this.skill == null) {
            return (this.pokemonName + " (" + this.getType() + ")");
        } else {
            return (this.pokemonName + " (" + this.getType() + "). Knows " + this.skill);
        }
    }


    public void setPokemonName(String newName) { this.pokemonName = newName; }


    public String getName() { return this.pokemonName; }
    public String getType() { return type.getType(); }
    public int getEnergy() { return this.currentEP; }
    public int getCurrentHP() { return this.currentHP; }
    public int getMAX_HP() { return this.MAX_HP; }

}
