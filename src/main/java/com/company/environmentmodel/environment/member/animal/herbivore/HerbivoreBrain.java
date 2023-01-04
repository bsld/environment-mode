package com.company.environmentmodel.environment.member.animal.herbivore;

import java.util.ArrayList;
import java.util.List;

import com.company.environmentmodel.environment.member.animal.brain.Brain;
import com.company.environmentmodel.environment.member.animal.brain.Input;
import com.company.environmentmodel.environment.member.animal.brain.Action;
import com.company.environmentmodel.environment.member.animal.carnivore.Wolf;
import com.company.environmentmodel.environment.member.plants.Carrot;
import com.company.environmentmodel.neuro.MlDataSet;

public class HerbivoreBrain extends Brain {

    @Override
    protected MlDataSet getTrainingSets() {
        List<Input> inputs = new ArrayList<>();
        List<Action> targets = new ArrayList<>();

        Input input = new Input();
        Action target = Action.MOVE;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Left */
        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));
        input.left(new Carrot(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));
        input.left(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.left(new Wolf(null));
        input.left(new Carrot(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));
        input.left(new Wolf(null));
        input.left(new Carrot(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.left(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.left(new Carrot(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Right */
        /*                                       */

        input = new Input();
        input.right(new Rabbit(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.right(new Rabbit(null));
        input.right(new Carrot(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.right(new Rabbit(null));
        input.right(new Wolf(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.right(new Wolf(null));
        input.right(new Carrot(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.right(new Rabbit(null));
        input.right(new Wolf(null));
        input.right(new Carrot(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.right(new Wolf(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.right(new Carrot(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Front */
        /*                                       */

        input = new Input();
        input.front(new Carrot(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.front(new Rabbit(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.front(new Rabbit(null));
        input.front(new Carrot(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.front(new Rabbit(null));
        input.front(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.front(new Wolf(null));
        input.front(new Carrot(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.front(new Rabbit(null));
        input.front(new Wolf(null));
        input.front(new Carrot(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.front(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Nearby */
        /*                                       */

        input = new Input();
        input.nearby(new Rabbit(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.nearby(new Rabbit(null));
        input.nearby(new Carrot(null));

        target = Action.EAT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.nearby(new Rabbit(null));
        input.nearby(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.nearby(new Wolf(null));
        input.nearby(new Carrot(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.nearby(new Rabbit(null));
        input.nearby(new Wolf(null));
        input.nearby(new Carrot(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.nearby(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        input = new Input();
        input.nearby(new Carrot(null));

        target = Action.EAT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Left and right */
        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));
        input.right(new Rabbit(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);
        
        
        input = new Input();
        input.left(new Rabbit(null));
        input.right(new Wolf(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);


        /*                                       */
        /* Left and front */
        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));
        input.front(new Rabbit(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);
        
        input = new Input();
        input.left(new Rabbit(null));
        input.front(new Wolf(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);
        
        input = new Input();
        input.left(new Rabbit(null));
        input.front(new Wolf(null));
        input.right(new Carrot(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Left and nearby */
        /*                                       */

        input = new Input();
        input.left(new Rabbit(null));
        input.nearby(new Rabbit(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);
        
        input = new Input();
        input.left(new Rabbit(null));
        input.nearby(new Rabbit(null));
        input.right(new Carrot(null));

        target = Action.TURN_RIGHT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Right and front */
        /*                                       */

        input = new Input();
        input.right(new Rabbit(null));
        input.front(new Rabbit(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Right and nearby */
        /*                                       */

        input = new Input();
        input.right(new Rabbit(null));
        input.nearby(new Rabbit(null));

        target = Action.TURN_LEFT;

        inputs.add(input);
        targets.add(target);

        /*                                       */
        /* Nearby and front */
        /*                                       */

        input = new Input();
        input.nearby(new Rabbit(null));
        input.front(new Rabbit(null));

        target = Action.MOVE;

        inputs.add(input);
        targets.add(target);

        /*                                       */

        return new MlDataSet(inputs, targets);
    }

}