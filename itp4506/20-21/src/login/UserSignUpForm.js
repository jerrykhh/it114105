import React, {Component} from 'react';
import './Login.css';
import { Link, Redirect } from 'react-router-dom';


class UserSignUpForm extends Component{

    constructor(props){
        super(props);
        this.state = {
            username: "",
            password: "",
            cpwd: "",
            email: "",
            phone: "",
            firstName: "",
            lastName: "",
            redirect: null
        }
    }

    handleChange = (event) => {
        let changeName = event.target.name;
        this.setState({[changeName]: event.target.value});
    }

    handleSignUp = () => {
        var isVaild = true;
        this.props.argue(null);
        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(this.state.username === "" || this.state.password === "" 
        || this.state.cpwd === "" || this.state.email === "" 
        || this.state.phone === "" || this.state.firstName === ""
        || this.state.lastName === ""){
            this.props.argue("Missing the required fields");
            isVaild = false;
        }else if(this.state.password !== this.state.cpwd){
            this.props.argue("Password is not match");
            isVaild = false;
        }else if (!re.test(String(this.state.email).toLowerCase())) {
            this.props.argue("Email format is incorrect");
            isVaild = false;
        }

        if(!isVaild){
            this.setState({password: "", cpwd: "" });
        }else{
           this.setState({redirect: "/index?reg=" + encodeURI(this.state.username)});
        }
        
    } 


    render = () => {
        const { redirect } = this.state;
        if (redirect != null) {
            return <Redirect to={this.state.redirect} />;
        }
        return(
            <div className="signup-form-detial-row">
                    <input type="text" name="username" onChange={this.handleChange} value={this.state.username} placeholder="Username"/>
                    <input type="password" name="password" onChange={this.handleChange} value={this.state.password} placeholder="Password"/>
                    <input type="password" name="cpwd" onChange={this.handleChange} value={this.state.cpwd} placeholder="Confirm Passoword"/>
                    <input type="email" name="email" onChange={this.handleChange} value={this.state.email} placeholder="Email"/>
                    <input type="tel" name="phone" onChange={this.handleChange} value={this.state.phone} placeholder="Phone Number"/>
                    <div className="input-container">
                        <input type="text" name="firstName" onChange={this.handleChange} value={this.state.firstName} placeholder="First Name"/>
                        <input type="text" name="lastName" onChange={this.handleChange} value={this.state.lastName} placeholder="Last Name"/>
                    </div>
                    <br/>
                    <Link to="index">Already have account?</Link>
                    <input type="button" onClick={this.handleSignUp} value="Sign Up"/>
            </div>
        );
    }


}

export default UserSignUpForm;