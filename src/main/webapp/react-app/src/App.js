
import Header from './components/Header';
import Footer from './components/Footer';
import Profile from './pages/Profile';
import Login from './pages/Login';
import Order from './pages/Order';
import Register from "./pages/Register";
import Vacancy from "./pages/Vacancy";
import Index from './pages/Index';

import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';



function App() {
    return (
        <div className={"no-height"} style={{height: "100%"}}>
            <Router>
                {/*<Header />*/}
                <Routes>

                    <Route exact path="/" element={<><Header /><Index /><Footer /></>}/>
                    <Route exact path="/profile" element={<div>
                        <Header />
                        <Profile />
                        <Footer />
                    </div>}/>
                    <Route exact path="/order" element={<div style={{display: "flex", flexDirection: "column", justifyContent: "space-between", height: "100%"}}>
                        <Header />
                        <Order />
                        <Footer />
                    </div>}/>
                    <Route exact path="/vacancy" element={<div>
                        <Header />
                        <Vacancy />
                        <Footer />
                    </div>}/>
                    <Route exact path="/login" element={<Login />}/>
                    <Route exact path="/register" element={<Register />}/>


                </Routes>
                {/*<Footer />*/}
            </Router>

        </div>
    );
}

export default App;
