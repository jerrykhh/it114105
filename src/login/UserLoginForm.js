import React, {Component} from 'react';
import { Link } from 'react-router-dom';
import './Login.css';
import user from '../server/user.json';
//import manager from '../server/agent.json';
//import agent from '../server/agent.json';

class UserLoginForm extends Component{
    constructor(props){
        super(props);
        this.state = {
            role: 'customer',
            username: '',
            password: ''
        }
    }

    changeRole = (roleName) =>{
        this.setState({role: roleName});
    }

    handleChange = (event) => {
        let eleName = event.target.name;
        this.setState({[eleName]: event.target.value});
    }

    handleLogin = () => {
        if(this.state.role === "customer"){
            console.log(user);
        }
    }

    SignUp = () => {
        if(this.state.role === "customer"){
            return <Link style={{fontSize: "10px", color: "black", right: 0}} to="/signup">Click here to create account</Link>
        }
    }
    
    render = () => {
        return(
            <div className="login-form-detial-row">
                <div className="login-form-detial-col-role">
                    <button className={(this.state.role === "customer")? "selected": ""} onClick={ () => this.changeRole("customer")}>Customer</button>
                    <button className={(this.state.role === "agent")? "selected": ""} onClick={ () => this.changeRole("agent")}>Agent</button>
                    <button className={(this.state.role === "manager")? "selected": ""} onClick={ () => this.changeRole("manager")}>Manager</button>
                </div>
                <div className="login-form-detial-col-input">
                    <form onSubmit={this.handleLogin}> 
                        <input type="text" name="username" onChange={this.handleChange} value={this.state.username} placeholder="Username" required/>
                        <input type="password" name="password" onChange={this.handleChange} value={this.state.password} placeholder="Password" required/>
                        <input type="hidden" name="role" value={this.state.role} />
                        {
                            (this.state.role === "customer") && <Link to="signup">Click here to create account</Link>
                        } 
                        <input type="submit" value="Login"/>
                    </form>
                </div>
            </div>
        );
    }

}

export default UserLoginForm;