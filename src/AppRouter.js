import React from 'react';
import { BrowserRouter, Route, Switch } from "react-router-dom";
import LoginForm from './login/LoginForm';
import SignUpForm from './login/SignUpForm';
import ManagerTemplate from './user/manager/ManagerTemplate';
//import SignUp from './SignUp';


class AppRouter extends React.Component {

    render() {
        return (
            <BrowserRouter>
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
                    <Route path='/manager/logout' component={ManagerTemplate} />
                    <Route path='/user/home' render={() => window.location.href = "http://localhost:8080/html/user-property.html?u=" + localStorage.getItem("username")}/>
                    <Route path='/agent/home' render={() => window.location.href = "http://localhost:8080/html/agent-property.html?u=" + localStorage.getItem("username")}/>
                </Switch>
            </BrowserRouter>
        );
    }

}

export default AppRouter;