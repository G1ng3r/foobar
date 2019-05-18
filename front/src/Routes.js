import React from 'react';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';

import Login from "./pages/login/Login"
import Main from "./pages/main/Main"
import ChallengeSearch from "./pages/challenge-search/ChallengeSearch"
import ChallengeSelect from "./pages/challenge-select/ChallengeSelect"
import ChallengeCurrent from "./pages/challenge-current/ChallengeCurrent"
import ChallengeTake from "./pages/challenge-take/ChallengeTake"
import IndexPage from "./pages/index-page/IndexPage"
import Header from "./components/header/Header"

import './App.css';

export default () => (
    <div>
        <BrowserRouter>
            <Switch>
                <Route path={'/'} exact={true} component={IndexPage} />
                <div className="App">
                    <Header/>
                    <Route path={'/main'} exact={true} component={Main}/>
                    <Route path={'/login'} exact={true} component={Login}/>
                    <Route path={'/challenge/search'} component={ChallengeSearch}/>
                    <Route path={'/challenge/select'} component={ChallengeSelect}/>
                    <Route path={'/challenge/take'} component={ChallengeTake}/>
                    <Route path={'/challenge/current'} component={ChallengeCurrent}/>
                    {/*<Route path={'/challenge'} component={Challenge}/>*/}
                    <Route path={'/settings'} exact={true} component={() => <h1>HUI VAM</h1>}/>
                </div>
                <Redirect to={'/main'}/>
            </Switch>
        </BrowserRouter>
    </div>
)
