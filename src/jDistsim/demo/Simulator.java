package jDistsim.demo;


import jDistsim.demo.simulation.calendar.Calendar;
import jDistsim.demo.simulation.calendar.ScheduleModule;

/**
 * Author: Jirka Pénzeš
 * Date: 23.12.12
 * Time: 20:42
 */
public class Simulator {

    private ISimulationModel model;
    private Calendar calendar;
    private double localTime;

    public Simulator(ISimulationModel model) {
        this.model = model;
        setLocalTime(0);
        calendar = new Calendar();
    }

    private void initialize() {
        for (RootModule module : model.getRootModules()) {
            module.execute(this, null);
        }
    }

    public void simulate() {
        initialize();

        while (localTime < 10) {
            //System.out.println(calendar.toString());

            ScheduleModule scheduleModule = calendar.poll();
            setLocalTime(scheduleModule.getTime());

            Module module = scheduleModule.getEventContainer().getModule();
            Entity entity = scheduleModule.getEventContainer().getEntity();

            module.execute(this, entity);
        }
    }

    public double getLocalTime() {
        return localTime;
    }

    public void setLocalTime(double localTime) {
        this.localTime = localTime;
    }

    public void plan(double time, Module module, Entity entity) {
        calendar.plan(time, module, entity);
    }

    public ISimulationModel getModel() {
        return model;
    }


}
