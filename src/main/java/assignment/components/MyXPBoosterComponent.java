package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;


public class MyXPBoosterComponent extends Component {

    // This component doubles the experience gained for the CONSTRUCTION skill

    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    // This event method is called when a GiveXpEvent is fired
    private void onGiveXP(GiveXpEvent event) {
        // Check if the skill is CONSTRUCTION
        if (event.getSkill() == Skill.CONSTRUCTION) {
            // Double the experience gained
            event.setXp(event.getXp() * 2);
        }
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
