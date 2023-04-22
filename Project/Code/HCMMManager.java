import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HCMMManager {

    //This methods loads the MembersFile and add it into the members ArrayList
    public void loadMembersData(String pathName, ArrayList<Member> members) {

        try {
            File filePath = new File(pathName);
            Scanner fileInfo = new Scanner(filePath);

            while(fileInfo.hasNextLine()) {
                Member member = new Member();
                member.setName(fileInfo.nextLine());
                member.setBirthday(fileInfo.nextLine());
                member.setPhoneNumber(fileInfo.nextLine());
                member.setMembershipType(fileInfo.nextLine());
                member.setFee(Double.parseDouble(fileInfo.nextLine()));
                members.add(member);
            }
            fileInfo.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void loadInstructionsFile(String pathName, ArrayList<Member> members, String reportFile) throws FileNotFoundException, IOException {

        File fw = new File(reportFile);
        PrintWriter output = new PrintWriter(fw);

        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {

            String line;
            while ((line = reader.readLine()) != null) {

                ArrayList<String> word = new ArrayList<>();
                String[] words = line.trim().split("[;\\s+;]");
      
                for(int i=0; i<words.length; i++) {
                    word.add(words[i]);
                }
      
                for(int i=0; i<word.size(); i++) {
                    if(word.get(i).equals("")) {
                        word.remove(word.get(i));
                    }
                }

                if (word.get(0).equals("add")) {

                  String name = word.get(2) + " " + word.get(3);
                  String birthday = word.get(5);
                  String pass = word.get(9);
                  String mobile = word.get(7);
                  double fee = Double.parseDouble(word.get(11));

                  boolean found = false;
                  int index = 0;
          
                  for(int i=0; i<members.size(); i++) {
                      if((members.get(i).getName().equals(name)) && (members.get(i).getPhoneNumber().equals(mobile))) {
                          found = true;
                          index = i;
                      }
                  }
          
                  if(found==true) {
                      members.get(index).setBirthday(birthday);
                      members.get(index).setFee(fee);
                      members.get(index).setMembershipType(pass);
                  } else {
                      Member newMember = new Member(name, birthday, mobile, pass, fee);
                      members.add(newMember);
                  }

                } else if (word.get(0).equals("delete")) {
                    String name = word.get(1) + " " + word.get(2);

                    for(int i=0; i<members.size(); i++) {
                        if(members.get(i).getName().equals(name)) {
                            members.remove(i);
                        }
                    }

                } else if (word.get(0).equals("query")) {
                    if(word.get(1).equals("pass")) {
                        String passType = word.get(2);

                        List<Member> goldType = members.stream()
                        .filter(goldMember -> goldMember.getMembershipType().equals(passType))
                        .collect(Collectors.toList());
        
                        // goldType.forEach(System.out::println);

                        output.print("----------- query pass " + passType + "-----------");
                        output.println();
                        for(Member whatevah:goldType) {
                            output.println(whatevah);
                        }

                    } else if(word.get(1).equals("age") && word.get(2).equals("fee")) {
                        int totalKidsFee = 0; 
                        int totalTeensFee = 0; 
                        int totalAdultsFee = 0;
                        int totalSeniorsFee = 0;
                        int unknown = 0;

                        for(Member sumn: members) {
                            if(sumn.getAge()<=8) {
                                totalKidsFee+=sumn.getFee();
                            } else if((sumn.getAge()>8) && (sumn.getAge()<18)) {
                                totalTeensFee+=sumn.getFee();
                            } else if((sumn.getAge()>18) && (sumn.getAge()<65)) {
                                totalAdultsFee+=sumn.getFee();
                            } else if(sumn.getAge()==65) {
                                totalSeniorsFee+=sumn.getFee();
                            } else {
                                unknown+=sumn.getAge();
                            }
                        }

                        output.println("----------- query age fee-----------");
                        output.println("Total Club Member size: " + members.size());
                        output.println("Age based fee income distribution");
                        output.println("[0,8]: $" + totalKidsFee);
                        output.println("[8,17]: $" + totalTeensFee);
                        output.println("[8,17]: $" + totalAdultsFee);                        
                        output.println("[65,-]: $" + totalSeniorsFee);
                        output.println("[Unknown]: $" + unknown);
                        
                    }
                }
          }
        }

        output.close();
    }

    // This function updates MembersFile
    public void updateMembersFile(String fileName, ArrayList<Member> members) throws FileNotFoundException {
        File fw = new File(fileName);
        PrintWriter output = new PrintWriter(fw);
        
        for(Member sumn:members) {
            output.println(sumn);
        }
        output.close();
    }

    // double fee = Double.parseDouble(parts[5].trim().substring(1));  // remove '$' and parse fee

}
