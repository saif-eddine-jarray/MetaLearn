import React, { useState } from 'react';
import { useFormik } from 'formik';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { classNames } from 'primereact/utils';
import './Step2Tutor.css';
import axios from 'axios';
import { Steps } from 'primereact/steps';
export function Step2Tutor(props) {
    const [showMessage, setShowMessage] = useState(false);
    const [formData, setFormData] = useState({});
    const formik = useFormik({
        initialValues: {
            firstname: '',
            lastname: ''
        },
        validate: (data) => {
            let errors = {};

            if (!data.firstname) {
                errors.firstname = 'Firstname is required.';
            }

            if (!data.lastname) {
                errors.lastname = 'Lastname is required.';
            }

            return errors;
        },
        onSubmit: (data) => {
            setFormData(data);
            const username=props.user.name;
            const password=props.user.password;
            const email=props.user.email;
            const firstName=data.firstname;
            const lastName=data.lastname;
            const tutor={firstName,lastName}
            const user={username,email,password,tutor};
            console.log(user);
            axios.post(`${process.env.REACT_APP_HOST}/user/save`,user)
            .then((Response) => {
              let id=Response.data.id;
              const template={id};
              props.Onsubmit(id);
              axios.post(`${process.env.REACT_APP_HOST}/login/validation`,template).then(()=>{
                setShowMessage(true);
                formik.resetForm();
              });
            })
        }
    });

    const isFormFieldValid = (name) => !!(formik.touched[name] && formik.errors[name]);
    const getFormErrorMessage = (name) => {
        return isFormFieldValid(name) && <small className="p-error">{formik.errors[name]}</small>;
    };

    const dialogFooter = <div className="flex justify-content-center"><Button label="OK" className="p-button-text" autoFocus onClick={() => {setShowMessage(false); props.Onsubmit2(true)}} /></div>;
    const items = [
        {label: 'Register'},
        {label: 'User details'},
        {label: 'Validation'},
        {label: 'Finalization'}
      ];
    return (
        <div>
            <Steps model={items} className='Steps' activeIndex={1}/>
            <div className="form-demo">
                <Dialog visible={showMessage} onHide={() => setShowMessage(false)} position="top" footer={dialogFooter} showHeader={false} breakpoints={{ '960px': '80vw' }} style={{ width: '30vw' }}>
                    <div className="flex align-items-center flex-column pt-6 px-3">
                        <i className="pi pi-check-circle" style={{ fontSize: '5rem', color: 'var(--green-500)' }}></i>
                        <h5>Registration Successful!</h5>
                        <p style={{ lineHeight: 1.5, textIndent: '1rem' }}>
                            Your account is registered under name <b>{formData.firstname}</b> . Please check <b>{props.user.email}</b> for activation instructions.
                        </p>
                    </div>
                </Dialog>

                <div className="flex justify-content-center">
                    <div className="card">
                        <form onSubmit={formik.handleSubmit} className="p-fluid">
                            <div className="field">
                                <span className="p-float-label">
                                    <InputText id="firstname" name="firstname" value={formik.values.firstname} onChange={formik.handleChange} autoFocus className={classNames({ 'p-invalid': isFormFieldValid('firstname') })} />
                                    <label htmlFor="firstname" className={classNames({ 'p-error': isFormFieldValid('name') })}>Firstname*</label>
                                </span>
                                {getFormErrorMessage('firstname')}
                            </div>
                            <div className="field">
                                <span className="p-float-label">
                                    <InputText id="lastname" name="lastname" value={formik.values.lastname} onChange={formik.handleChange} className={classNames({ 'p-invalid': isFormFieldValid('lastname') })} />
                                    <label htmlFor="lastname" className={classNames({ 'p-error': isFormFieldValid('lastname') })}>Lastname*</label>
                                </span>
                                {getFormErrorMessage('lastname')}
                            </div>
                            <Button type="submit" label="Submit" className="mt-2" />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}