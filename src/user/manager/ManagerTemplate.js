import React from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import {Link} from 'react-router-dom';
import SupervisorAccountRoundedIcon from '@material-ui/icons/SupervisorAccountRounded';
import PersonRoundedIcon from '@material-ui/icons/PersonRounded';
import ListAltRoundedIcon from '@material-ui/icons/ListAltRounded';
import ChatRoundedIcon from '@material-ui/icons/ChatRounded';
import AccountCircle from '@material-ui/icons/AccountCircle';
import HouseIcon from '@material-ui/icons/House';
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';
import {Switch, Route} from 'react-router-dom';
import ManagerCustomer from './ManagerCustomer';
import ManagerAgent from './ManagerAgent';
import ManagerProfile from './ManagerProfile';
import ManagerAppointment from './ManagerAppointment';
import ManagerComment from './ManagerComment';
import ManagerCustomerDetail from './ManagerCustomerDetail';
import ManagerProperty from './ManagerProperty'



const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
  },
  appBar: {
    zIndex: theme.zIndex.drawer + 1,
    backgroundColor: 'black',
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
  },
  appBarShift: {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  menuButton: {
    marginRight: 0,
  },
  hide: {
    display: 'none',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
  },
  drawerOpen: {
    width: drawerWidth,
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  },
  drawerClose: {
    transition: theme.transitions.create('width', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: 'hidden',
    width: theme.spacing(7) + 1,
    [theme.breakpoints.up('sm')]: {
      width: theme.spacing(9) + 1,
    },
    
  },
  toolbar: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(3),
  }, 
  title: {
    flexGrow: 1
  }
}));


export default function ManagerTemplate() {
  const classes = useStyles();
  const theme = useTheme();
  const [open, setOpen] = React.useState(false);
  const [auth, setAuth] = React.useState(true);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const openProfile = Boolean(anchorEl);
  

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const handleMenu = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleLogout = () => {

  }


  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar
        position="fixed"
        className={clsx(classes.appBar, {
          [classes.appBarShift]: open,
        })}
      >
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            edge="start"
            className={clsx(classes.menuButton, {
              [classes.hide]: open,
            })}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            Dream House Online
          </Typography>
          {auth && (
            <div>
              <IconButton
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleMenu}
                color="inherit"
              >
                <AccountCircle />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorEl}
                anchorOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'right',
                }}
                open={openProfile}
                onClose={handleClose}
              >
                <Link to='/manager/profile'><MenuItem>Profile</MenuItem></Link>
                <MenuItem onClick={handleLogout}>Logout</MenuItem>
              </Menu>
            </div>
          )}
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        className={clsx(classes.drawer, {
          [classes.drawerOpen]: open,
          [classes.drawerClose]: !open,
        })}
        classes={{
          paper: clsx({
            [classes.drawerOpen]: open,
            [classes.drawerClose]: !open,
          }),
        }}>
        <div className={classes.toolbar}>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </div>
        <Divider />
        <List>
            <Link to="appointment">
                <ListItem button key="Appointments">
                <ListItemIcon>
                    <ListAltRoundedIcon/>
                </ListItemIcon>
                <ListItemText primary="Appointments" />
                </ListItem>
            </Link>
            <Link to="property">
                <ListItem button key="property">
                <ListItemIcon>
                    <HouseIcon/>
                </ListItemIcon>
                <ListItemText primary="property" />
                </ListItem>
            </Link>
            <Link to="comment">
                <ListItem button key="Comment">
                <ListItemIcon>
                    <ChatRoundedIcon/>
                </ListItemIcon>
                <ListItemText primary="Comment" />
                </ListItem>
            </Link>
            <Link to="customer" >
                <ListItem button key="Customer">
                <ListItemIcon>
                    <PersonRoundedIcon/>
                </ListItemIcon>
                <ListItemText primary="Customer" />
                </ListItem>
            </Link>
            <Link to="agent">
                <ListItem button key="Property Agent">
                <ListItemIcon>
                    <SupervisorAccountRoundedIcon/>
                </ListItemIcon>
                <ListItemText primary="Property Agent" />
                </ListItem>
            </Link>
        </List>
        <Divider />
      </Drawer>
      <main className={classes.content}>
        <div className={classes.toolbar} />
        <Switch>
              <Route path="/manager/home" component={ManagerCustomer}/>
              <Route path="/manager/profile" component={ManagerProfile}/>
              <Route path="/manager/appointment" component={ManagerAppointment}/>
              <Route path="/manager/comment" component={ManagerComment}/>
              <Route path="/manager/customer/:id" component={ManagerCustomerDetail}/>
              <Route path="/manager/customer" component={ManagerCustomer}/>
              <Route path="/manager/property" component={ManagerProperty}/>
              <Route path='/manager/agent' component={ManagerAgent} />
        </Switch>
      </main>
    </div>
  );
}
