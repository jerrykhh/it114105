import React from 'react';
import { HashRouter, Route, Switch } from "react-router-dom";
import LoginForm from './login/LoginForm';
import SignUpForm from './login/SignUpForm';
import ManagerHome from './user/manager/ManagerHome';
//import SignUp from './SignUp';


class AppRouter extends React.Component {

    render() {
        return (
            <HashRouter>
                <Switch>
                    <Route exact path="/" component={LoginForm} />
                    <Route path="/index" component={LoginForm} />
                    <Route path="/signup" component={SignUpForm}/>
                    <Route path="/manager/home" component={ManagerHome}/>
                    
                </Switch>
            </HashRouter>
        );
    }

}

export default AppRouter;