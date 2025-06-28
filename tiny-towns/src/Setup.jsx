import ErrorBox from "./ErrorBox";
import AddPlayer from "./AddPlayer";
import Button from "./Button";
import './Setup.css';

export default function Setup({players, handleAddPlayer, handleStartGame, errorMessage, currentPlayerId, code}) {

    return (
        <div className="setup">
          {errorMessage && <ErrorBox message={errorMessage} />}
          {!currentPlayerId? 
              <p className="setup-instructions">Add players and click Start Game when ready to begin:</p>
            : <p className="setup-instructions">Have other players open this game with game code {code} so they can join the game. Click Start Game when ready to begin.</p>}
    
          <div className="player-list">
            {players.map((player, index) => (
              <p key={index}>{player.name}</p>
            ))}
          </div>
    
          {!currentPlayerId && <AddPlayer handleAddPlayer={handleAddPlayer} />}
    
          <Button onClick={handleStartGame} className="mt-3">
            Start Game
          </Button>
        </div>
      );
}