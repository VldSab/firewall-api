import * as React from "react";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import { Box, Button, TextField } from "@mui/material";
import { Formik, Field } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../components/Header";
import Methods from "../components/Methods";
import Protocols from "../components/Protocols";
import { ExposureOutlined } from "@mui/icons-material";

const FormDialog = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleFormSubmit = (values) => {};

  return (
    <Box>
      <Button variant="outlined" onClick={handleClickOpen} color="secondary">
        ADD GROUP
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Create rule</DialogTitle>
        <DialogContent>
          Create a New Rule
          <Formik
            onSubmit={handleFormSubmit}
            initialValues={initialValues}
            validationSchema={checkoutSchema}
          >
            {({
              values,
              errors,
              touched,
              handleBlur,
              handleChange,
              handleSubmit,
            }) => (
              <form onSubmit={handleSubmit}>
                <Box
                  display="grid"
                  gap="30px"
                  gridTemplateColumns="repeat(4, minmax(0, 1fr))"
                  sx={{
                    "& > div": {
                      gridColumn: isNonMobile ? undefined : "span 4",
                    },
                  }}
                >
                  <Field
                    value={values.method}
                    name="method"
                    type="text"
                    component={Methods}
                    label="Method"
                    margin="normal"
                    variant="outlined"
                    disabled={false}
                    onChange={handleChange}
                    sx={{ gridColumn: "span 2" }}
                  />
                  <Field
                    value={values.protocol}
                    name="protocol"
                    type="text"
                    component={Protocols}
                    label="Protocol"
                    margin="normal"
                    variant="outlined"
                    disabled={false}
                    onChange={handleChange}
                    sx={{ gridColumn: "span 2" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="Source IPs"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.sourceIPs}
                    name="sourceIPs"
                    error={!!touched.sourceIPs && !!errors.sourceIPs}
                    helperText={touched.sourceIPs && errors.sourceIPs}
                    sx={{ gridColumn: "span 2" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="Source Ports"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.sourcePorts}
                    name="sourcePorts"
                    error={!!touched.sourcePorts && !!errors.sourcePorts}
                    helperText={touched.sourcePorts && errors.sourcePorts}
                    sx={{ gridColumn: "span 2" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="Destination IPs"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.destinationIPs}
                    name="destinationIPs"
                    error={!!touched.destinationIPs && !!errors.destinationIPs}
                    helperText={touched.destinationIPs && errors.destinationIPs}
                    sx={{ gridColumn: "span 2" }}
                  />
                  <TextField
                    fullWidth
                    variant="filled"
                    type="text"
                    label="Destination Ports"
                    onBlur={handleBlur}
                    onChange={handleChange}
                    value={values.destinationPorts}
                    name="destinationPorts"
                    error={
                      !!touched.destinationPorts && !!errors.destinationPorts
                    }
                    helperText={
                      touched.destinationPorts && errors.destinationPorts
                    }
                    sx={{ gridColumn: "span 2" }}
                  />
                </Box>
                <Box display="flex" justifyContent="center" mt="40px">
                  <Button
                    type="submit"
                    color="secondary"
                    variant="contained"
                    sx={{ width: "25%", height: "100%" }}
                  >
                    Create
                  </Button>
                </Box>
              </form>
            )}
          </Formik>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="secondary">Cancel</Button>
          <Button onClick={handleClose} color="secondary">Ok</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

const checkoutSchema = yup.object().shape({
  method: yup.string().required("required"),
  protocol: yup.string().required("required"),
  sourceIPs: yup.string().required("required"),
  sourcePorts: yup.string().required("required"),
  destinationIPs: yup.string().required("required"),
  destinationPorts: yup.string().required("required"),
});
const initialValues = {
  method: "",
  protocol: "",
  sourceIPs: "",
  sourcePorts: "",
  destinationIPs: "",
  destinationPorts: "",
};

export default FormDialog;
