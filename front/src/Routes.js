import React from 'react';
import { BrowserRouter, Redirect, Route, Switch } from 'react-router-dom';

import Login from "./pages/login/Login"
import Request from "./pages/request/Request"
import Challenge from "./pages/challenge/Challenge"
import Main from "./pages/main/Main"

export default () => (
    <div>
        <BrowserRouter>
            <Switch>
                <Route path={'/'} exact={true} component={Main}/>
                <Route path={'/login'} exact={true} component={Login}/>
                <Route path={'/request'} component={Request}/>
                <Route path={'/challenge'} component={Challenge}/>
                <Route path={'/settings'} exact={true} component={() => <h1>HUI VAM</h1>}/>
                <Redirect to={'/'}/>
            </Switch>
            <Route/>
        </BrowserRouter>
    </div>
)
