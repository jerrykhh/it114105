import React from 'react';
import { HashRouter, Route, Switch } from "react-router-dom";
import LoginForm from './login/LoginForm';
import SignUpForm from './login/SignUpForm';
import ManagerTemplate from './user/manager/ManagerTemplate';
//import SignUp from './SignUp';


class AppRouter extends React.Component {

    render() {
        return (
            <HashRouter>
                <Switch>
                    <Route exact path="/" component={LoginForm} />
                    <Route path="/index" component={LoginForm} />
                    <Route path="/signup" component={SignUpForm}/>
                    <Route path="/manager/home" component={ManagerTemplate}/>
                    <Route path="/manager/profile" component={ManagerTemplate}/>
                    <Route path="/manager/appointment/:id" component={ManagerTemplate}/>
                    <Route path="/manager/appointment" component={ManagerTemplate}/>
                    <Route path="/manager/comment" component={ManagerTemplate}/>
                    <Route path="/manager/customer/:id" component={ManagerTemplate}/>
                    <Route path="/manager/customer" component={ManagerTemplate}/>
                    <Route path="/manager/property" component={ManagerTemplate}/>
                    <Route path="/manager/agent" component={ManagerTemplate}/>
                </Switch>
            </HashRouter>
        );
    }

}

export default AppRouter;