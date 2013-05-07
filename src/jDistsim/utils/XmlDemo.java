package jDistsim.utils;

import jDistsim.application.designer.common.UIConfiguration;
import jDistsim.core.simulation.distributed.DistributedModelDefinition;
import jDistsim.core.simulation.modules.lib.NullModule;
import jDistsim.utils.collection.observable.ObservableList;
import jDistsim.utils.pattern.observer.IObserver;
import jDistsim.utils.pattern.observer.Observable;
import jDistsim.utils.persistence.Persistor;

import java.io.FileOutputStream;

/**
 * Author: Jirka Pénzeš
 * Date: 23.3.13
 * Time: 10:02
 */
public class XmlDemo {

    private static class Box {
        private Object obj1;
        private Object obj2;
    }

    private static class Panda implements IObserver {
        private String pandas;
        private int number;

        public Panda() {
        }

        public Panda(String n, int numb) {
            this.pandas = n;
            this.number = numb;
        }

        public String getName() {
            return pandas + "/" + number;
        }

        @Override
        public void update(Observable observable, Object arguments) {
            System.out.println("HERE!");
        }
    }

    public static void main(String[] args) {
        System.out.println("XML serializable demo");

        Panda panda = new Panda("Po", 1);
        ObservableList observableList = new ObservableList();
        NullModule module = new NullModule(new DistributedModelDefinition("model1", "localhost", 9011));
        //observableList.add(module);
        // module.addObserver(panda);

        FileOutputStream os = null;
        try {
            //os = new FileOutputStream("C:/Users/Jirka/Desktop/modules.persistence");

            Box box = new Box();
            box.obj1 = panda;
            box.obj2 = module;

            Persistor.save(UIConfiguration.getInstance().getColorSchemeForBasicModule(), "C:/Users/Jirka/Desktop/demo.persistence");
            System.out.println("Serialize start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
