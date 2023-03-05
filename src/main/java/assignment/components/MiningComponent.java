package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.EventMethod;
import net.gameslabs.api.Player;
import net.gameslabs.events.MineOreEvent;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.AssignmentFailed;
import net.gameslabs.model.Ore;
import net.gameslabs.model.Skill;
import net.gameslabs.implem.PlayerImplem;

import java.util.HashMap;
import java.util.Map;

public class MiningComponent extends Component {
    // Declare private variables
    private Map<Player, Integer> miningLevels;
    private final Map<Ore, Integer> oreXp;

    // Create constructor
    public MiningComponent() {
        miningLevels = new HashMap<>();
        oreXp = new HashMap<>();
        oreXp.put(Ore.COAL, 10);
        oreXp.put(Ore.IRON, 20);
        oreXp.put(Ore.GOLD, 30);
        oreXp.put(Ore.RUNITE, 40);
    }

    // Register MineOreEvent
    @Override
    public void onLoad() {
        registerEvent(MineOreEvent.class, this::onMineOre);
    }

    // Event method for MineOreEvent
    private void onMineOre(MineOreEvent event) {
        Player player = event.getPlayer();
        Ore ore = event.getOre();
        int miningLevel = getMiningLevel(player);

        int xp;
        // Check mining level and assign xp accordingly
        switch (ore) {
            case COAL:
                if (miningLevel < 5) {
                    throw new AssignmentFailed("Player does not have high enough mining level to mine coal");
                }
                xp = 10;
                break;

            case IRON:
                if (miningLevel < 10) {
                    throw new AssignmentFailed("Player does not have high enough mining level to mine iron");
                }
                xp = 20;
                break;

            case GOLD:
                if (miningLevel < 20) {
                    throw new AssignmentFailed("Player does not have high enough mining level to mine gold");
                }
                xp = 30;
                break;

            case RUNITE:
                if (miningLevel < 30) {
                    throw new AssignmentFailed("Player does not have high enough mining level to mine runite");
                }
                xp = 40;
                break;

            default:
                throw new AssignmentFailed("Invalid ore type");
        }
        // Send GiveXpEvent with the player and xp gained
        send(new GiveXpEvent(player, Skill.MINING, xp));
    }

    // Method to get the mining level of a player
    private int getMiningLevel(Player player) {
        return miningLevels.computeIfAbsent(player, p -> 1);
    }

    // Unload method
    @Override
    public void onUnload() {
        // Do nothing
    }
}