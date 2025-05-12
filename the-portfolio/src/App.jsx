import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import giffGatinho from './assets/gatinho_coracao.gif'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <div>
      <h1>Bommm dia meu amor</h1>
      <h1>EU AMO VOCÊ! ❤️</h1>
      <h2>
        <img src={giffGatinho} alt='Gatinho Coração' />
      </h2>
    </div>
    </>
  )
}

export default App
