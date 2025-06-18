import { useState, useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import './Header.css';
import logo from './img/tiny-towns-logo.png';
import api from './api';

export default function Header() {
    const [inputtedGameCode, setInputtedGameCode] = useState('');
    const navigate = useNavigate();
    const {code: gameCode} = useParams();

  
    const newGame = () => {
        api.newGame().then((response) => {
            navigate(`/${response.data.code}`);
        }).catch((error) => {
          console.error('Error:', error);
        });
    }

    const handleOpenGame = () => {
      if (inputtedGameCode.trim()) {
        navigate(`/${inputtedGameCode}`);
      }
    };

    return (
        <header className='app-header'>
            <img src={logo} className="app-logo" alt="Tiny Towns" />
            {gameCode && (<span className="gameCode">GAME CODE: {gameCode}</span>)}
            <div className='app-options'>
                <span className="link" onClick={newGame}>NEW GAME</span>
                <span>|</span>
                <span className="link" onClick={handleOpenGame}>OPEN GAME</span>
                <input
                    type="text"
                    placeholder="Enter Game Code"
                    value={inputtedGameCode}
                    onChange={(e) => setInputtedGameCode(e.target.value)}
                />
            </div>
        
        </header>
    );
}