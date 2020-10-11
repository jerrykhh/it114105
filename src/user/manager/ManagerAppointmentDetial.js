import React, { Component } from 'react';
import Button from '@material-ui/core/Button';
import ArrowBackIosIcon from '@material-ui/icons/ArrowBackIos';
import { Link } from 'react-router-dom';
import data from '../../server/appointment.json';
import user from '../../server/user.json';
import agent from '../../server/agent.json';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import { withStyles, makeStyles } from '@material-ui/core/styles';


const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    }
}))(TableCell);

const useStyles = makeStyles({
    root: {
      minWidth: 275
    },
    bullet: {
      display: 'inline-block',
      margin: '0 2px',
      transform: 'scale(0.8)',
    },
    title: {
      fontSize: 14,
    },
    pos: {
      marginBottom: 12,
    },
  });


class ManagerAppointmentDetial extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ap: {},
            user: {},
            agent: {}
        };
    }

    componentDidMount = () => {
        const id = decodeURI(this.props.match.params.id);
        this.setState({id: id});
        console.log(id);
        data.forEach((obj, index) => {
            var userInf = null;
            var agentInf = null;
            console.log(obj.id);
            if (obj.uid == id) {
                user.forEach((userObj, index) => {
                    userObj.appointments.forEach((item) => {
                        if (item === obj.uid) {
                            userInf = userObj;
                        }
                    });
                });

                agent.forEach((agentObj, index) => {
                    agentObj.appointments.forEach((item) => {
                        if (item === obj.uid) {
                            agentInf = agentObj;
                        }
                    });
                });

                this.setState({ ap: obj, user: userInf, agent: agentInf })
            }

        });
        console.log("init");

    }

    render = () => {
        console.log(this.state);
        return (
            <div>
                <Link to='../appointment'>
                    <Button
                        variant="contained"
                        color="primary"
                        size="small"
                        startIcon={<ArrowBackIosIcon />}>
                        Back
                    </Button>
                </Link>
                <Paper variant="outlined" square style={{ marginTop: 20, padding: 50, paddingLeft: 90, marginBottom: 20 }}>
                    <div>
                        <h1 style={{ textAlign: "left" }}>Appointment Information</h1>
                        <br />
                        <p className="user-inf-details">
                            Id: {this.state.id}
                            <br />
                            Status: {this.state.ap.status}
                            <br/>
                            Dated: {this.state.ap.date}
                            <br/>
                            Place: {this.state.ap.place}
                            <br />
                        </p>
                    </div>
                    <TableContainer style={{ marginTop: 50 }}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell>Agent Infromation</StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                <TableRow>
                                    <p style={{fontSize: 14, paddingLeft: 10}}>
                                        <br/>
                                        Username: {this.state.agent.username}
                                        <br/>
                                        Name: {this.state.agent.firstName + " " + this.state.agent.lastName}
                                        <br/>
                                        Email: {this.state.agent.email}
                                        <br/>
                                        Tel: {this.state.agent.tel}
                                    </p>
                                    {
                                        (this.state.ap.cfa !== "")?
                                        <Card className={useStyles.root} style={{marginTop: 10, marginBottom: 30}} variant="outlined">
                                        <CardContent>
                                          <Typography className={useStyles.title} color="textSecondary" gutterBottom>
                                            Comment from Agent:
                                          </Typography>
                                          <Typography variant="h5" component="h2">
                                          </Typography>
                                          <Typography variant="body2" component="p">
                                            {this.state.ap.cfa}
                                          </Typography>
                                        </CardContent>
                                      </Card>: ""
                                    }
                                </TableRow>
                                
                            </TableBody>
                            <br/>
                            <TableHead>
                                <TableRow>
                                    <StyledTableCell><div style={{float: "right"}}>Customer Infromation</div></StyledTableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody style={{textAlign: 'right'}}>
                                <p style={{fontSize: 14, paddingRight: 10}}>
                                    <br/>
                                    Username: {this.state.agent.username}
                                    <br/>
                                    Name: {this.state.agent.firstName + " " + this.state.agent.lastName}
                                    <br/>
                                    Email: {this.state.agent.email}
                                    <br/>
                                    Tel: {this.state.agent.tel}
                                </p>
                                {
                                        (this.state.ap.cfu !== "")?
                                        <Card className={useStyles.root} style={{marginTop: 10, marginBottom: 30}} variant="outlined">
                                        <CardContent>
                                          <Typography className={useStyles.title} color="textSecondary" gutterBottom>
                                            Comment from Agent:
                                          </Typography>
                                          <Typography variant="h5" component="h2">
                                          </Typography>
                                          <Typography variant="body2" component="p">
                                            {this.state.ap.cfa} ({this.state.ap.rfu + "/10"})
                                          </Typography>
                                        </CardContent>
                                      </Card>: ""
                                    }
                            </TableBody>
                        </Table>
                    </TableContainer>

                </Paper>
            </div>
        );
    }
}

export default ManagerAppointmentDetial;