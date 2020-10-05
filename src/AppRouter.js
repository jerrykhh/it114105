import React from 'react';
import { HashRouter, Route, Switch } from "react-router-dom";
import LoginForm from './login/LoginForm';
import SignUpForm from './login/SignUpForm';
//import SignUp from './SignUp';


class AppRouter extends React.Component {

    render() {
        return (
            <HashRouter>
                <Switch>
                    <Route exact path="/" component={LoginForm} />
                    <Route path="/index" component={LoginForm} />
                    <Route path="/signup" component={SignUpForm}/>
                </Switch>
            </HashRouter>
        );
    }

}

export default AppRouter;