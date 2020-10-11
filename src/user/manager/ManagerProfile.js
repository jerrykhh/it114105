import React, { Component } from 'react';
import { withStyles } from "@material-ui/core/styles";
import TextField from '@material-ui/core/TextField';
import Paper from '@material-ui/core/Paper';
import managerJson from '../../server/manager.json';
import "../style.css";

const styles = theme => ({
    root: {
        flexGrow: 1,
        overflow: 'hidden',

    },
    profile: {
        flex: 1,
        width: '50%',
        margin: '0 auto',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 40
    },
    textField: {
        width: '100%',
        marginBottom: 30,

    },
    profileContent: {
        padding: 80,
        margin: '0 auto',
        width: '90%',
    },
    btnSave: {
        backgroundColor: '#3897f0',
        color: 'white',
    },
    lblUserInf: {
        marginTop: 20,
        marginBottom: 10
    },
    profileMessage: {
        marginBottom: 10
    }

});

class ManagerProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            username: null,
            email: null,
            tel: null,
            firstName: null,
            lastName: null,
            password: null,
            currPassword: null,
            newPassword: null,
            conPassword: null,
            errMess: [],
            sucMess: []
        };
    }

    componentDidMount = () => {
        let id = localStorage.getItem("id");
        managerJson.forEach((obj, index) => {
            console.log(id, obj.id);
            if (obj.id == id) {
                console.log("fd")
                this.setState({
                    username: obj.username,
                    email: obj.email,
                    tel: obj.tel,
                    firstName: obj.firstName,
                    lastName: obj.lastName,
                    password: obj.password,
                });
                return;
            } else {
                console.log(obj);
            }
        });
        console.log(id, this.state, "test");

    }

    handleSave = (event) => {
        console.log(this.state)
        this.setState((state, props) => ({
            sucMess: [],
            errMess: []
        }));

        var isValid = true;

        if (this.state.username === "" && this.state.email === "" &&
            this.state.tel === "" && this.state.firstName === "" &&
            this.state.lastName === "") {
            this.saveStateErrMess("Missing All fields");
            isValid = false;
            return;
        }
        if (this.state.username === "") {
            this.saveStateErrMess("Missing the Username");
            isValid = false;
        }
        const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (this.state.email === "") {
            this.saveStateErrMess("Missing the Email");
            isValid = false;
        } else if (!re.test(String(this.state.email).toLowerCase())) {
            this.saveStateErrMess("Email format is incorrect");
        }
        if (this.state.tel === "") {
            this.saveStateErrMess("Missing the Tel");
            isValid = false;
        }
        if (this.state.firstName === "") {
            this.saveStateErrMess("Missing the First Name");
            isValid = false;
        }
        if (this.state.lastName === "") {
            this.saveStateErrMess("Missing the Last Name");
            isValid = false;
        }

        var updatePassword = false;
        var passwordIsvaild = true;
        if (this.state.newPassword !== "" && this.state.newPassword !== null) {
            updatePassword = true;
            if (this.state.currPassword !== this.state.password) {
                this.saveStateErrMess("Current Password is not match");
                isValid = false;
                passwordIsvaild = false
            }
            if (this.state.newPassword !== this.state.conPassword) {
                this.saveStateErrMess("New Password and Confirm Password is not match");
                isValid = false;
                passwordIsvaild = false;
            }
        }

        if (isValid) {
            if (!updatePassword) {
                this.setState({ sucMess: this.state.sucMess.concat("User information Saved") });
            } else {
                if (passwordIsvaild)
                    this.setState({ sucMess: this.state.sucMess.concat("User information and password Saved") });
            }

        }

    }

    saveStateErrMess = (mes) => {
        console.log(mes);
        setTimeout(() => {
            this.setState({ errMess: this.state.errMess.concat(mes) });
        }, 100);
        console.log(this.state)
    }

    handleChange = (event) => {
        let changeName = event.target.name;
        this.setState({ [changeName]: event.target.value });
    }

    render = () => {
        console.log("rssrs");
        console.log(this.state.errMess);
        const { classes } = this.props;
        var mesList = null;
        if (this.state.errMess.length > 0) {

            if (this.state.errMess.length > 1) {
                var errMess = "";
                console.log("remder")
                console.log(this.state.errMess);
                this.state.errMess.map((message) => {
                    errMess += message + ", ";
                })
                mesList = <div className="bs-callout bs-callout-danger"><h4>Update Failed</h4>{errMess.slice(0, -2)}</div>
            } else {
                mesList = this.state.errMess.map((message) => <div className="bs-callout bs-callout-danger"><h4>Update Failed</h4>{message}</div>);
            }

        } else if (this.state.sucMess.length > 0) {
            mesList = <div className="bs-callout bs-callout-success"><h4>Update Successful</h4>{this.state.sucMess[0]}</div>;
        }
        console.log(this.state.sucMess.length + "ds");
        return (
            <Paper variant="outlined" className={classes.root}>
                <div className={classes.profile}>
                    <Paper variant="outlined" className={classes.profileContent} square>
                        <div className={classes.profileMessage}>{mesList}</div>
                        <h1>Profile</h1>
                        <div className={classes.profileInput}>
                            <p>
                                <h4 className={classes.lblUserInf}>User Information</h4>
                                <TextField name="username" className={classes.textField} id="username" label="Username" variant="outlined" value={this.state.username} onChange={this.handleChange} />
                                <TextField name="email" className={classes.textField} id="email" label="Email" variant="outlined" value={this.state.email} onChange={this.handleChange} />
                                <TextField name="tel" className={classes.textField} id="tel" label="Tel" variant="outlined" value={this.state.tel} onChange={this.handleChange} />
                                <TextField name="firstName" className={classes.textField} style={{ width: '47%' }} id="firstName" label="First Name" variant="outlined" value={this.state.firstName} onChange={this.handleChange} />
                                <TextField name="lastName" className={classes.textField} style={{ width: '48%', marginLeft: '5%' }} id="lastName" label="Last Name" variant="outlined" value={this.state.lastName} onChange={this.handleChange} />
                            </p>
                            <p>
                                <h4 className={classes.lblUserInf}>Update Password</h4>
                                <TextField type="password" name="currPassword" className={classes.textField} id="currPassword" label="Current Password" variant="outlined" onChange={this.handleChange} />
                                <TextField type="password" name="newPassword" className={classes.textField} id="newPassword" label="New Password" variant="outlined" onChange={this.handleChange} />
                                <TextField type="password" name="conPassword" className={classes.textField} id="conPassword" label="Confirm Password" variant="outlined" onChange={this.handleChange} />
                            </p>
                        </div>

                        <input type="button" value="Save" className={classes.btnSave} onClick={this.handleSave} />
                    </Paper>
                </div>
            </Paper>
        );
    }


}

export default withStyles(styles, { withTheme: true })(ManagerProfile);