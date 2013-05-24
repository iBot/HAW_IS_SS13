package game;

/**
 * Created with IntelliJ IDEA.
 * User: Alexander
 * Date: 15.05.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public interface IGui {
    /**
     * Feld markieren, im Rahmen von 1-20. Oberstes Feld ist 1, weiter im Uhrzeigersinn
     *
     * @param fieldnr Feld-ID
     * @param player  Spieler (1, schwarz; 2, weiss)
     * @return true für erfolgreiches markieren, false bei Fehlern (belegtes Feld/Bereich über-/unterschritten)
     */
    public boolean setField(int fieldnr, int player) throws OffBoardException;

    /**
     * Gibt Belegung auf Feld fieldnr zurück
     * @param fieldnr Abzufragendes Feld
     * @return Belegung: 0 - Unbelegt, grau; 1 - Spieler 1, schwarz; 2 - Spieler 2, weiss
     */
    public int getField(int fieldnr);

    /**
     * Um informiert zu werden, sobald ein menschlicher Spieler einen Spielzug beendet, muss man sich für das Event einschreiben.
     *
     * @param playerMovedListener Listener
     */
    public void subscribeForPlayerMovedEvet(PlayerMovedListener playerMovedListener);

    class OffBoardException extends Exception {
        private String message;

        OffBoardException(String m){
            this.message = m;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
