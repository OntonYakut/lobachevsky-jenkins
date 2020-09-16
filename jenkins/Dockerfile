FROM jenkins/jenkins:latest

USER root:root

RUN curl -L https://services.gradle.org/distributions/gradle-6.5.1-bin.zip --output .files/gradle-6.5.1-bin.zip \
    --create-dirs
RUN unzip -q .files/gradle-6.5.1-bin.zip -d .files/gradle/
RUN mkdir -p /data/gradle
RUN mv .files/gradle/gradle-6.5.1/* /data/gradle/
RUN chown -R jenkins:jenkins /data/gradle/*
RUN rm -rf .files

ENV PATH=${PATH}:/data/gradle:/data/gradle/bin:/data/gradle/lib

USER jenkins:jenkins
