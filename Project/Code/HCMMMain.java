import java.io.IOException;
import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

public class HCMMMain {

    public static void main(String[] args) throws IOException {

        ArrayList<Member> members = new ArrayList<>();
        String membersFile = "C:\\Users\\calla\\Desktop\\sample\\eme\\blessie\\Project\\Code\\membersFile.txt";
        String reportFile = "C:\\Users\\calla\\Desktop\\sample\\eme\\blessie\\Project\\Code\\reportFile.txt";
        String instructionsFile = "C:\\Users\\calla\\Desktop\\sample\\eme\\blessie\\Project\\Code\\instructionsFile.txt";

        HCMMManager hcmmManager = new HCMMManager();
        hcmmManager.loadMembersData(membersFile, members);
        hcmmManager.loadInstructionsFile(instructionsFile, members, reportFile);
        hcmmManager.updateMembersFile(membersFile, members);

    }
    
}
