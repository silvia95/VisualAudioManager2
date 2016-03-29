package visualAudioManager;

public class PwdCommand implements Command {

    public PwdCommand() {

    }

    @Override
    public void execute() {
        System.out.println(CurrentDirectory.getInstance().getCurrentDirectory());
    }
}
