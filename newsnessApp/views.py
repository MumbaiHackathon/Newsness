from django.shortcuts import render
from django.shortcuts import redirect
from django.http import HttpResponse
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.http import HttpResponseRedirect
from django.core.urlresolvers import reverse
from newsnessApp.models import publishedArticles, submittedDrafts
import twittersentimentalanalysis, wikipediasummary
# Create your views here.

baseurl = "http://127.0.0.1:8000/"

def home(request):
    return render(request, "home.html")

def user_login(request,redirectwebsite,htmlpagetorender):

    """
    Function for handling authentication of a user
    :param request object.
    """

    if request.method =="POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(username=username, password=password)
        if user:
            if user.is_active:
                login(request,user)

                return HttpResponseRedirect(redirectwebsite)
            else:
                return HttpResponse("Your account has been diasbled")
        else:
            return HttpResponse("Invalid Username/Password")
    else:
        return render(request, htmlpagetorender)

def user_logout(request):
    logout(request)
    return HttpResponseRedirect(baseurl)

def journalistslogin(request):
    return user_login(request,"/journalistshome/","index.html")

@login_required
def journalistshome(request):

    submitted_drafts = submittedDrafts.objects.filter(writer = request.user)

    return render_to_response(
        'journalisthome.html',
        {'submittedDrafts': submitted_drafts},
        context_instance=RequestContext(request)
    )

@login_required
def publishedarticlsfetcher(request):

    # Load documents for the list page
    published_Articles = publishedArticles.objects.filter(writer = request.user)

    return render_to_response(
        'publishedArticles.html',
        {'publishedArticles': published_Articles},
        context_instance=RequestContext(request)
    )

@login_required
def submitteddraftsfetcher(request):

    # Load documents for the list page
    submitted_drafts = submittedDrafts.objects.filter(writer = request.user)

    return render_to_response(
        'submittedDrafts.html',
        {'submittedDrafts': submitted_drafts},
        context_instance=RequestContext(request)
    )

@login_required
def researchresults(request):
    tanalysis = twittersentimentalanalysis.main()
    wiki = wikipediasummary.getsummary("Narendra Modi")
    return render_to_response(
        'researchresults.html',
        {'positivesentimentpercentage': tanalysis[0],
        'negativesentimentpercentage' : tanalysis[1],
        'neutralsentimentpercentage' : 100-tanalysis[0]-tanalysis[1],
        'wiki':wiki},
        context_instance=RequestContext(request)
    )
