import { TabMenu } from 'primereact/tabmenu';
import { InputText } from 'primereact/inputtext';
import { Menubar } from 'primereact/menubar';
import { useRef, useState } from 'react';
import { Avatar } from 'primereact/avatar';
import { Button } from 'primereact/button';
import './Navbar.css'
import { OverlayPanel } from 'primereact/overlaypanel';
import { Divider } from 'primereact/divider';
import { Card } from 'primereact/card';
import { useNavigate } from 'react-router-dom';
const Navbar = (props) => {
    const items = [
        {label: 'Home', icon: 'pi pi-fw pi-home', url:'/'},
        {label: 'Fields & Courses', icon: 'pi pi-fw pi-pencil', url: '/courses'},
        {label: 'Settings', icon: 'pi pi-fw pi-cog', url:'/settings'},
        {label: 'About us', icon: 'pi pi-fw pi-file', url:'/aboutus' },
        {label: '______________________________________', disabled:true}
    ];
    const item=[
        {label: 'Profile', icon: 'pi pi-fw pi-star-fill',className:'m', items:[
            {label: 'Log in', icon:'pi pi-fw pi-user',url:'/login'},
            {label: 'Register', icon:'pi pi-fw pi-user-plus', url:'/registration'}
        ]}
    ]
    let navigate=useNavigate();
    const op=useRef(null);
    const [activeIndex, setActiveIndex] = useState();
    const items1 = [
        {label: 'Home', icon: 'pi pi-fw pi-home'},
        {label: 'Calendar', icon: 'pi pi-fw pi-calendar'},
        {label: 'Edit', icon: 'pi pi-fw pi-pencil'},
        {label: 'Documentation', icon: 'pi pi-fw pi-file'},
        {label: 'Settings', icon: 'pi pi-fw pi-cog'}
    ];
    return (
            <div className='header'>
                {localStorage.getItem("user") &&
                <OverlayPanel ref={op} showCloseIcon id="overlay_panel" style={{width: '300px'}} className="overlaypanel-demo panel">
                <div>
                <h2>Hello, {JSON.parse(localStorage.getItem("user")).username[0].toUpperCase()+JSON.parse(localStorage.getItem("user")).username.substring(1)}</h2>
                <Divider />
                    <ul className='profile'>
                        <li id='profile'>
                        <Button className='list'>Profile</Button>
                        </li>
                        <li id='profile'>
                        <Button className='list'>Courses</Button>
                        </li>
                        <li id='profile'>
                        <Button className='list'>Settings</Button>
                        </li>
                    </ul>
                <Button className='logout' onClick={(e)=>{localStorage.clear();navigate('/login')}}>Log out</Button>
                </div>
                </OverlayPanel>}
                <TabMenu model={items} />
                {localStorage.getItem("authenticated") &&  <Button onClick={(e) => op.current.toggle(e)} aria-haspopup aria-controls="overlay_panel" className="select-product-button"><Avatar label={JSON.parse(localStorage.getItem("user")).username[0].toUpperCase()} className="mr-2" size="large" style={{ backgroundColor: '#9c27b0', color: '#ffffff' }} shape='circle' /></Button>}
                {!localStorage.getItem("authenticated") && <Menubar model={item} className='m'/>}
                <span className="p-input-icon-left" >
                    <i className="pi pi-search" />
                    <InputText placeholder="Search" className='search' />
                </span>
                     
            </div>
    )
};

export default Navbar;