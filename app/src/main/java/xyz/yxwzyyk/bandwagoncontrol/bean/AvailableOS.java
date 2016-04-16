package xyz.yxwzyyk.bandwagoncontrol.bean;

import java.util.List;

/**
 * Created by yyk on 4/16/16.
 */
public class AvailableOS {

    /**
     * error : 0
     * installed : debian-7.0-x86
     * templates : ["centos-5-x86","centos-5-x86-devel","centos-5-x86_64","centos-5-x86_64-devel","centos-6-x86","centos-6-x86-devel","centos-6-x86-minimal","centos-6-x86_64","centos-6-x86_64-devel","centos-6-x86_64-minimal","centos-7-x86_64","centos-7-x86_64-minimal","debian-6-turnkey-nginx-php-fastcgi_12.0-1_i386","debian-6.0-x86","debian-6.0-x86-minimal","debian-6.0-x86_64","debian-6.0-x86_64-minimal","debian-7.0-x86","debian-7.0-x86-minimal","debian-7.0-x86_64","debian-7.0-x86_64-minimal","debian-8.0-x86_64","debian-8.0-x86_64-minimal","fedora-19-x86","fedora-19-x86_64","fedora-20-x86","fedora-20-x86_64","fedora-21-x86_64","fedora-22-x86_64","oracle-6-x86-20130522","oracle-6-x86_64-20130522","scientific-6-x86","scientific-6-x86_64","suse-12.2-x86","suse-12.2-x86_64","suse-12.3-x86","suse-12.3-x86_64","suse-13.1-x86","suse-13.1-x86-minimal","suse-13.1-x86_64","suse-13.1-x86_64-minimal","ubuntu-10.04-x86","ubuntu-10.04-x86_64","ubuntu-12.04-x86","ubuntu-12.04-x86-minimal","ubuntu-12.04-x86_64","ubuntu-12.04-x86_64-minimal","ubuntu-12.10-x86","ubuntu-12.10-x86_64","ubuntu-13.04-x86","ubuntu-13.04-x86_64","ubuntu-13.10-x86","ubuntu-13.10-x86_64","ubuntu-14.04-x86","ubuntu-14.04-x86-minimal","ubuntu-14.04-x86_64","ubuntu-14.04-x86_64-minimal","ubuntu-15.04-x86_64","ubuntu-15.04-x86_64-minimal","ubuntu-15.10-x86_64","ubuntu-15.10-x86_64-minimal"]
     */

    private int error;
    private String installed;
    private List<String> templates;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getInstalled() {
        return installed;
    }

    public void setInstalled(String installed) {
        this.installed = installed;
    }

    public List<String> getTemplates() {
        return templates;
    }

    public void setTemplates(List<String> templates) {
        this.templates = templates;
    }
}
