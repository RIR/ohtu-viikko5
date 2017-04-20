package ohtu;

public class TennisGame {

    private int p1Score = 0;
    private int p2Score = 0;
    private final String player1Name;
    private final String player2Name;
    private String score;
    private final String[] pointStrings;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        score = "";
        pointStrings = new String[]{"Love", "Fifteen", "Thirty", "Forty"};
    }

    public void wonPoint(String playerName) {
        addPoint(playerName);
    }

    private void addPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            p1Score++;
        } else {
            p2Score++;
        }
    }

    public String getScore() {
        setScore();
        return score;
    }

    private void setScore() {
        if (overDeuceLimit(p1Score) || overDeuceLimit(p2Score)) {
            playByAdvantageScoring();
        } else {
            playByPointScoring();
        }
    }

    private void playByPointScoring() {
        score = gameIsEven() ? getPointString(p1Score) + "-All" : getPointString(p1Score) + "-" + getPointString(p2Score);
    }

    private void playByAdvantageScoring() {
        if (gameIsEven()) {
            score = "Deuce";
        } else if (isAdvantage(getScoreDifference())) {
            score = "Advantage " + getLeadingPlayer();
        } else if (isWin(getScoreDifference())) {
            score = "Win for " + getLeadingPlayer();
        }
    }

    private String getPointString(int playerScore) {
        return pointStrings[playerScore];
    }

    private String getLeadingPlayer() {
        return p1Score > p2Score ? "player1" : "player2";
    }

    private int getScoreDifference() {
        return p1Score - p2Score;
    }

    private boolean overDeuceLimit(int score) {
        int deuceLimit = 3;
        return score > deuceLimit;
    }

    private boolean gameIsEven() {
        return p1Score == p2Score;
    }

    private static boolean isWin(int difference) {
        int winLimit = 2;
        return Math.abs(difference) >= winLimit;
    }

    private static boolean isAdvantage(int difference) {
        int advantage = 1;
        return Math.abs(difference) == advantage;
    }
}
