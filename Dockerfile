FROM python:3.11
MAINTAINER Belevski97 "andrei_belevski@abv.com"
COPY app.py test.py /app/
WORKDIR /app
RUN pip install flask pytest flake8
CMD ["python", "app.py"]