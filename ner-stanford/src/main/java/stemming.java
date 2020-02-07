public class stemming {

    public static void main(String[] args) {

        String text = "Hello, I wanted to schedule a meeting with John. Scheduling means meeting has been scheduled.";
        text = text.toLowerCase();

        Stemmer s = new Stemmer();

        for (char ch : text.toCharArray()) {
            s.add(ch);
        }

        s.stem();
        String u = s.toString();
        System.out.println(u);


    }

}
