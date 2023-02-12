FROM node:latest

WORKDIR /

ADD package.json /package.json
ADD package-lock.json /package-lock.json
ADD src /src
ADD public /public

RUN npm install

ENTRYPOINT npm run start