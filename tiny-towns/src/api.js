import axios from 'axios';
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

const instance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

const api = {
  refresh: (gameCode) => instance.get(`/${gameCode}`),
  newGame: () => instance.post('/newGame'),
  addPlayer: (gameCode, playerName) =>
    instance.post(`/${gameCode}/addPlayer`, null, {
      params: { playerName },
    }),
  startGame: (gameCode) =>
    instance.post(`/${gameCode}/startGame`),
  namePiece: (gameCode, piece) =>
    instance.post(`/${gameCode}/namePiece`, null, {
      params: { piece },
    }),
  placePiece: (gameCode, playerId, gridIndex) =>
    instance.post(`/${gameCode}/placePiece`, null, {
      params: { playerId, gridIndex },
    }),
  build: (gameCode, playerId, gridIndex, indexes, building) =>
    instance.post(`/${gameCode}/buildPiece`, indexes, {
      params: { playerId, gridIndex, building },
    }),
  endTurn: (gameCode) =>
    instance.post(`/${gameCode}/endTurn`),
};

instance.interceptors.response.use(
  response => response,
  error => {
    console.log(error);
    const res = error?.response?.data;

    if (res?.errorCode && res?.message) {
      error.userMessage = res.message;
    } else {
      error.userMessage = "Error.";
    }

    return Promise.reject(error);
  }
);

export default api;
