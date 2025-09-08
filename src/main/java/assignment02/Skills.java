package assignment02;

public class Skills {
    private final String skillName;
    private final int attackPower;
    private final int energyCost;

    // CONSTANTS TYPE MULTIPLIERS
    private final double notEffective = 0.5;
    private final double superEffective = 2;
    private final double regularAttack = 1;

    public Skills(String name, int attackPower, int energyCost) {
        this.skillName = name;
        this.attackPower = attackPower;
        this.energyCost = energyCost;
    }

    // Returns true if two skills are equal
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        } else if (anotherObject == null) {
            return false;
        } else if (anotherObject instanceof Skills) {
            Skills otherSkill = (Skills) anotherObject;
            boolean sameSkillName = this.skillName.equals(otherSkill.skillName);
            boolean sameAP = this.attackPower == otherSkill.attackPower;
            boolean sameEC = this.energyCost == otherSkill.energyCost;

            return sameSkillName && sameAP && sameEC;
        } else {
            return false;
        }
    }


    // Returns <multiplier_variable> and passes it back through method chain:
    // attacking Pokémon: attack() > target Pokémon: receiveDamage() > typeMultiplier()
    public double typeMultiplier(Pokemon attacker, Pokemon defender){
        switch (attacker.getType()) {
            case "Bug":
                switch (defender.getType()) {
                    case "Fire":
                        return notEffective;
                    case "Grass":
                        return superEffective;
                    default:
                        return regularAttack;
                }
            case "Dragon":
                switch (defender.getType()) {
                    case "Dragon":
                        return superEffective;
                    default:
                        return regularAttack;
                }
            case "Electric":
                switch (defender.getType()) {
                    case "Dragon":
                    case "Electric":
                    case "Grass":
                        return notEffective;
                    case "Water":
                        return superEffective;
                    default:
                        return regularAttack;
                }

            case "Fire":
                switch (defender.getType()) {
                    case "Dragon":
                    case "Fire":
                    case "Water":
                        return notEffective;
                    case "Bug":
                    case "Grass":
                    case "Ice":
                        return superEffective;
                    default:
                        return regularAttack;
                }
            case "Grass":
                switch (defender.getType()) {
                    case "Bug":
                    case "Dragon":
                    case "Fire":
                    case "Grass":
                        return notEffective;
                    case "Water":
                        return superEffective;
                    default:
                        return regularAttack;
                }
            case "Ice":
                switch (defender.getType()) {
                    case "Fire":
                    case "Ice":
                    case "Water":
                        return notEffective;
                    case "Dragon":
                    case "Grass":
                        return superEffective;
                    default:
                        return regularAttack;
                }
            case "Water":
                switch (defender.getType()) {
                    case "Dragon":
                    case "Grass":
                    case "Water":
                        return notEffective;
                    case "Fire":
                        return superEffective;
                    default:
                        return regularAttack;
                }
        }
        // case "Normal":      //since the Normal type doesn't have type advantages/disadvantages, this is the default return value
        return regularAttack;
    }

    // Returns different Strings based on type effectiveness
    public String typeEffectivenessMessage(Pokemon attacker, Pokemon targetPokemon) {
        switch ((int) typeMultiplier(attacker, targetPokemon)){
            case 1:
                return "";
            case 2:
                return " It is super effective!";
            default:
                return " It is not very effective...";
        }
    }

    // Returns String if the target faints from the attack
    public String willPokemonFaint (Pokemon targetPokemon) {
        if (targetPokemon.getCurrentHP() == 0) {
            return " " + targetPokemon.getName() + " faints.";
        } else {
            return ""; }
    }


    public String toString() { return(this.skillName + " - AP: " + this.attackPower + " EC: " + this.energyCost); }


    public String getSkillName() { return this.skillName; }
    public int getAttackPower() { return this.attackPower; }
    public int getEnergyCost() { return this.energyCost; }

}
