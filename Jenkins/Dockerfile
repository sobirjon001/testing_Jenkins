FROM maven:3.9.5-amazoncorretto-11

RUN yum install -y curl jq tr shadow-utils

WORKDIR /var/src/app

COPY ./src ./src
COPY ./pom.xml ./pom.xml
COPY ./Jenkins ./Jenkins

ARG USERNAME=jenkins
ARG GROUPNAME=jenkins
ARG UID=1000
ARG GID=1000

RUN groupadd --gid $GID $USERNAME
RUN useradd --uid $UID --gid $GID -m $USERNAME

RUN chmod -R $USESNAME:$GROUPNAME

USER $UID:$GID

ARG USER_HOME_DIR="/home/$USERNAME"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

RUN mkdir results
RUN mkdir downloads

RUN echo "Build successful!"