public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        for (PowerControl p : reg.getAllOfCapability(PowerControl.class)) {
            p.powerOn();
        }

        InputControl input = reg.getFirstOfCapability(InputControl.class);
        input.connectInput("HDMI-1");

        BrightnessControl lights = reg.getFirstOfCapability(BrightnessControl.class);
        lights.setBrightness(60);

        TemperatureControl ac = reg.getFirstOfCapability(TemperatureControl.class);
        ac.setTemperatureC(24);

        ScanningControl scan = reg.getFirstOfCapability(ScanningControl.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (PowerControl p : reg.getAllOfCapability(PowerControl.class)) {
            p.powerOff();
        }
    }
}
