import React, {Component} from 'react';
import { Link, Redirect } from 'react-router-dom';
import './Login.css';
import userJson from '../server/user.json';
import managerJson from '../server/manager.json';
import agentJson from '../server/agent.json';

class UserLoginForm extends Component{
    constructor(props){
        super(props);
        this.state = {
            role: 'customer',
            username: '',
            password: '',
            redirect: null
        }
    }

    changeRole = (roleName) =>{
        this.props.argue(null);
        this.setState({role: roleName});
    }

    handleChange = (event) => {
        let eleName = event.target.name;
        this.setState({[eleName]: event.target.value});
    }

    handleLogin = (event) => {
        event.preventDefault();
        this.props.argue(null);
        var user = null;
        var route = null;
        if(this.state.role === "customer"){
            user = userJson;
            route = "/home";
        }else if(this.state.role === "agent"){
            user = agentJson;
            route = "/agent/home"
        }else if(this.state.role === "manager"){
            user = managerJson;
            route = "/manager/home"
        }
        var isNotFound = true;
        user.forEach((userObj, index) => {
            if(userObj.username === this.state.username && userObj.password === this.state.password){
                this.setState({redirect: route});
                isNotFound = false;
                localStorage.setItem('username', this.state.username);
                localStorage.setItem('id', userObj.id);
                return;
            }else{
                if(userObj.username === this.state.username && userObj.password !== this.state.password){
                    this.props.argue("Password is not correct", 0);
                    this.setState({redirect: null});
                    isNotFound = false;
                }
            }
        });
        if(isNotFound){
            this.props.argue("Username or Password is not correct");
            this.setState({redirect: null});
        }
    }

    SignUp = () => {
        if(this.state.role === "customer"){
            return <Link style={{fontSize: "10px", color: "black", right: 0}} to="/signup">Click here to create account</Link>
        }
    }
    
    render = () => {
        const { redirect } = this.state;
        if (redirect != null) {
            return <Redirect to={this.state.redirect} />;
        }
        return(
            <div className="login-form-detial-row">
                <div className="login-form-detial-col-role">
                    <button className={(this.state.role === "customer")? "selected": ""} onClick={ () => this.changeRole("customer")}>Customer</button>
                    <button className={(this.state.role === "agent")? "selected": ""} onClick={ () => this.changeRole("agent")}>Agent</button>
                    <button className={(this.state.role === "manager")? "selected": ""} onClick={ () => this.changeRole("manager")}>Manager</button>
                </div>
                <div className="login-form-detial-col-input">
                    <form> 
                        <input type="text" name="username" onChange={this.handleChange} value={this.state.username} placeholder="Username" required/>
                        <input type="password" name="password" onChange={this.handleChange} value={this.state.password} placeholder="Password" required/>
                        <input type="hidden" name="role" value={this.state.role} />
                        {
                            (this.state.role === "customer") && <Link to="signup">Click here to create account</Link>
                        } 
                        <input type="button" className="login-form-btnLogin" value="Login" onClick={this.handleLogin}/>
                    </form>
                </div>
            </div>
        );
    }

}

export default UserLoginForm;