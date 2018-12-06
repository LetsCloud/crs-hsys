var cacheName = '{{cacheName}}';
var filesToCache = [  {{#each filesToCache}}
'{{.}}'{{#unless @last}},{{/unless}}
    {{/each}}
    ];

// INSTALL A SERVICE WORKER
//
// Inside of our install callback, we need to take the following steps:
// 1. Open a cache.
// 2. Cache our files.
// 3. Confirm whether all the required assets are cached or not.
//
// Here you can see we call caches.open() with our desired cache name, after
// which we call cache.addAll() and pass in
// our array of files. This is a chain of promises (caches.open() and
// cache.addAll()). The event.waitUntil() method
// takes a promise and uses it to know how long installation takes, and whether
// it succeeded or not.
//
// If all the files are successfully cached, then the service worker will be
// installed. If any of the files fail to
// download, then the install step will fail. This allows you to rely on having
// all the assets that you defined,
// but does mean you need to be careful with the list of files you decide to
// cache in the install step. Defining a
// long list of files will increase the chance that one file may fail to cache,
// leading to your service worker not
// getting installed.
self.addEventListener('install', function (e) {
	console.log('[ServiceWorker] Install');
    e.waitUntil(
        caches.open(cacheName)
        	.then(function (cache) {
        		console.log('[ServiceWorker] Caching app shell');
        		return cache.addAll(filesToCache);
        	})
    );
});

//UPDATE SERVICE WORKER
//
self.addEventListener('activate', function (e) {
 console.log('[ServiceWorker] Activate');
 e.waitUntil(
     caches.keys().then(function (keyList) {
         return Promise.all(keyList.map(function (key) {
             console.log('[ServiceWorker] Removing old cache', key);
             if (key !== cacheName) {
                 return caches.delete(key);
             }
         }));
     })
 );
});

// CACHE AND RETURN REQUESTS
//
// After a service worker is installed and the user navigates to a different
// page or refreshes, the service worker
// will begin to receive fetch events
//

self.addEventListener('fetch', function(e) {
//    console.log('[ServiceWorker] Fetch', e.request.url);
    e.respondWith(
        // Try the cache
        caches.match(e.request).then(function(response) {
            // Fall back to network
            return response || fetch(e.request);
        })
    );
});

// Set up a listener for messages posted from the service worker.
// The service worker is set to post a message to all its clients once it's run
// its activation
// handler and taken control of the page, so you should see this message event
// fire once.
// You can force it to fire again by visiting this page in an Incognito window.
self.addEventListener('message', function (e) {
    console.log('[Service Worker] message->' + e);
    if (e.data == 'skipWaiting') {
        self.skipWaiting();
    }
});


//
// Notifications API
//

// The notificationclick event is fired to indicate that a system notification
// spawned
// by ServiceWorkerRegistration.showNotification() has been clicked.
self.addEventListener('notificationclick', event => {
	console.log('On notification click.');

	event.notification.close();

	const msgPayload = event.notification.data;
	const clickAction =  msgPayload['data']['click_action'];
	var pos = clickAction.indexOf("#");
	const rootUrl = clickAction.substr(0,pos);
	
	if (!clickAction) {
		// Nothing to do.
	    return;
	}

	const promiseChain = this.getWindowClient_(rootUrl)
		.then(windowClient => {
	        if (!windowClient) {
	          // Unable to find window client so need to open one.
	          return clients.openWindow(clickAction);
	        }
	        return windowClient;
	 	})
	 	.then(windowClient => {
	 		if (!windowClient) {
	 			// Window Client will not be returned if it's for a third party origin.
	 			return;
	 		}

	 		const internalMsg = {
	 				'firebase-messaging-msg-type': 'notification-clicked',
	 				'firebase-messaging-msg-data': msgPayload
	 		};

	 		// Attempt to send a message to the client to handle the data
	        // Is affected by: https://github.com/slightlyoff/ServiceWorker/issues/728
	        return this.attemptToMessageClient_(windowClient, internalMsg);
	 	});

		event.waitUntil(promiseChain);	
});


// Import and configure the Firebase SDK
// These scripts are made available when the app is served or deployed on
// Firebase Hosting
// If you do not serve/host your project using Firebase Hosting see
// https://firebase.google.com/docs/web/setup
importScripts('https://www.gstatic.com/firebasejs/4.11.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.11.0/firebase-messaging.js');

firebase.initializeApp({'messagingSenderId': '1069431891980'});
	  
const messaging = firebase.messaging();

// If you would like to customize notifications that are received in the
// background (Web app is closed or not in browser focus) then you should
// implement this optional method.
// [START background_handler]
messaging.setBackgroundMessageHandler(function(msgPayload) {
	
	console.log('Received background message.');
    
	var notificationTitle = msgPayload['data']['title'];
	var notificationOptions = {
		body: msgPayload['data']['body'], 
		icon: msgPayload['data']['icon'],
		data: msgPayload
	};

	return self.registration.showNotification(notificationTitle, notificationOptions);
});

//
// @private
// @param {string} url The URL to look for when focusing a client.
// @return {Object} Returns an existing window client or a newly opened
// WindowClient.
//
function getWindowClient_(url) {
	// Use URL to normalize the URL when comparing to windowClients.
	// This at least handles whether to include trailing slashes or not
	const parsedURL = new URL(url).href;

	return clients.matchAll({
		type: 'window',
		includeUncontrolled: true
    })
    .then(clientList => {
    	let suitableClient = null;
    	for (let i = 0; i < clientList.length; i++) {
    		const parsedClientUrl = new URL(clientList[i].url).href;
    		if (parsedClientUrl.startsWith(parsedURL)) {
    			suitableClient = clientList[i];
    			break;
    		}
    	}

    	if (suitableClient) {
    		suitableClient.focus();
    		return suitableClient;
    	}
    });
}

//
// This message will attempt to send the message to a window client.
// @private
// @param {Object} client The WindowClient to send the message to.
// @param {Object} message The message to send to the client.
// @returns {Promise} Returns a promise that resolves after sending the
// message. This does not guarantee that the message was successfully
// received.
//
function attemptToMessageClient_(client, message) {
	return new Promise((resolve, reject) => {
		if (!client) {
			return reject(
					console.log('No Window Client')    		  
			);
		}

		client.postMessage(message);
		resolve();
	});
}
// [END background_handler]
