import { Box, Button, TextField } from "@mui/material";
import { Formik, Field } from "formik";
import * as yup from "yup";
import useMediaQuery from "@mui/material/useMediaQuery";
import Header from "../../components/Header";
import Methods from "../../components/util/Methods";
import Protocols from "../../components/util/Protocols";
import Modes from "../../components/util/Modes";
import Groups from "../../components/util/Groups";
import { getGroupIdByName } from "../../data/GroupsData";

const Firewall = () => {
  const isNonMobile = useMediaQuery("(min-width:600px)");

  const handleFormSubmit = (values) => {
    var body;
    
    if (values.mode === "CREATE SINGLE RULE") {
      body = JSON.stringify({
        action: values.method,
        protocol: values.protocol,
        srcIPs: values.sourceIPs,
        srcPorts: values.sourcePorts,
        dstIPs: values.destinationIPs,
        dstPorts: values.destinationPorts,
      });
    } else {
      var srcGroupID = getGroupIdByName(values.srcGroup);
      var dstGroupID = getGroupIdByName(values.dstGroup)
      body = JSON.stringify({ 
        action: values.method,
        protocol: values.protocol,
        srcGroupID: srcGroupID,
        dstGroupID: dstGroupID,
      })
    }

    fetch("http://localhost:8080/rules", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: body,
    })
      .then((response) => console.log(JSON.stringify(response)))
      .then(
        JSON.stringify({
          action: values.method,
          protocol: values.protocol,
          srcIPs: values.sourceIPs,
          srcPorts: values.sourcePorts,
          dstIPs: values.destinationIPs,
          dstPorts: values.destinationPorts,
        })
      );
  };

  return (
    <Box m="20px">
      <Header title="CREATE RULE" subtitle="Create a New Rule" />

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
                "& > div": { gridColumn: isNonMobile ? undefined : "span 4" },
              }}
            >
              <Field
                values={values.mode}
                value={values.mode}
                name="mode"
                type="text"
                component={Modes}
                label="Mode"
                margin="normal"
                variant="outlined"
                disabled={false}
                onChange={handleChange}
                sx={{ gridColumn: "span 4" }}
              />
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
              {values.mode === "USE GROUPS" ? (
                <Field
                  value={values.srcGroup}
                  name="srcGroup"
                  type="text"
                  component={Groups}
                  label="Source Group"
                  margin="normal"
                  variant="outlined"
                  disabled={false}
                  onChange={handleChange}
                  sx={{ gridColumn: "span 2" }}
                />
              ) : null}
              {values.mode === "USE GROUPS" ? (
                <Field
                  value={values.dstGroup}
                  name="dstGroup"
                  type="text"
                  component={Groups}
                  label="Destination Group"
                  margin="normal"
                  variant="outlined"
                  disabled={false}
                  onChange={handleChange}
                  sx={{ gridColumn: "span 2" }}
                />
              ) : null}
              {values.mode === "CREATE SINGLE RULE" ? (
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
              ) : null}
              {values.mode === "CREATE SINGLE RULE" ? (
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
              ) : null}
              {values.mode === "CREATE SINGLE RULE" ? (
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
              ) : null}
              
              {values.mode === "CREATE SINGLE RULE" ? (
              <TextField
                fullWidth
                variant="filled"
                type="text"
                label="Destination Ports"
                onBlur={handleBlur}
                onChange={handleChange}
                value={values.destinationPorts}
                name="destinationPorts"
                error={!!touched.destinationPorts && !!errors.destinationPorts}
                helperText={touched.destinationPorts && errors.destinationPorts}
                sx={{ gridColumn: "span 2" }}
              />
              ) : null}
            </Box>
            <Box display="flex" justifyContent="center" mt="40px">
              <Button
                type="submit"
                color="secondary"
                variant="contained"
                sx={{ width: "25%", height: "100%" }}
              >
                Create New Rule
              </Button>
            </Box>
          </form>
        )}
      </Formik>
    </Box>
  );
};

const checkoutSchema = yup.object().shape({
  mode: yup.string(),
  srcGroup: yup.string(),
  dstGroup: yup.string(),
  method: yup.string(),
  protocol: yup.string(),
  sourceIPs: yup.string(),
  sourcePorts: yup.string(),
  destinationIPs: yup.string(),
  destinationPorts: yup.string(),
});
const initialValues = {
  mode: "",
  srcGroup: "",
  dstGroup: "",
  method: "",
  protocol: "",
  sourceIPs: "",
  sourcePorts: "",
  destinationIPs: "",
  destinationPorts: "",
};

export default Firewall;
