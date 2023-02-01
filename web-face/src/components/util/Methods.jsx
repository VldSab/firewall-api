import { MenuItem, Box } from "@mui/material";
import { TextField } from 'formik-mui';
import { useEffect, useState } from "react";
const Methods = (props) => {

    const [countries, setCountries] = useState([]);

    useEffect(() => {
        const loadData = () => {
            setCountries(["DROP", "PASS"])
        }
        loadData();
    }, []);

    return (
        <TextField 
            select
            {...props}
        >
            {countries.length ?
                countries.map((country) => (
                    <MenuItem key={country} value={country}>
                        {country}
                    </MenuItem>
                ))
                :
                <MenuItem>loading...</MenuItem>
            }
        </TextField >
    );

};

export default Methods;