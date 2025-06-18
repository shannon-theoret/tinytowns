import './App.css';
import Game from './Game'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './Header';

function App() {

  return (
    <div className='App'>
      <Router>
        <Routes>
          <Route exact path="/" element={
            <Header/>}/>
          <Route path="/:code" element={
            <>
              <Header/>
              <Game/>
            </>
            }/>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
