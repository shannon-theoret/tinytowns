import ErrorBox from "./ErrorBox";
import AddPlayer from "./AddPlayer";
import Button from "./Button";
import './Setup.css';

export default function Setup({players, handleAddPlayer, handleStartGame, errorMessage}) {
    return (
        <div className="setup">
          {errorMessage && <ErrorBox message={errorMessage} />}
    
          <p className="setup-instructions">Add players and click Start Game when ready to begin:</p>
    
          <div className="player-list">
            {players.map((player, index) => (
              <p key={index}>{player.name}</p>
            ))}
          </div>
    
          <AddPlayer handleAddPlayer={handleAddPlayer} />
    
          <Button onClick={handleStartGame} className="mt-3">
            Start Game
          </Button>
        </div>
      );
}