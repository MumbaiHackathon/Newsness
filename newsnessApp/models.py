from __future__ import unicode_literals
from django.db import models
from django.contrib.auth.models import User
# Create your models here.

class journalistsDrafts(models.Model):
    writer = models.ForeignKey(User, null=True, blank=True)
    title = models.CharField(max_length=128,blank=False, null=True)
    body = models.TextField(blank=False, null=True)
    notes = models.TextField(blank=True, null=True)

    def __unicode__(self):
        return u'%s' %(self.title)

class submittedDrafts(models.Model):
    writer = models.ForeignKey(User, null=True, blank=True)
    title = models.CharField(max_length=128,blank=False, null=True)
    body = models.TextField(blank=False, null=True)

    def __unicode__(self):
        return u'%s' %(self.title)

class publishedArticles(models.Model):
    writer = models.CharField(max_length=128,blank=True, null=True)
    title = models.CharField(max_length=128,blank=False, null=True)
    body = models.TextField(blank=False, null=True)

    def __unicode__(self):
        return u'%s' %(self.title)
